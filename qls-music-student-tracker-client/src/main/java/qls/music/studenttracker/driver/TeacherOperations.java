package qls.music.studenttracker.driver;

import java.io.Console;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import qls.music.studenttracker.client.model.ClassEnrollment;
import qls.music.studenttracker.client.model.ClassEnrollmentId;
import qls.music.studenttracker.client.model.Classroom;
import qls.music.studenttracker.client.model.IncompleteSubmissions;
import qls.music.studenttracker.client.model.Journal;
import qls.music.studenttracker.client.model.JournalId;
import qls.music.studenttracker.client.model.JournalMaster;
import qls.music.studenttracker.client.model.Student;
import qls.music.studenttracker.client.model.Teacher;
import qls.music.studenttracker.client.model.UngradedSubmissions;
import qls.music.studenttracker.rest.client.StudentTrackerRestClient;

public class TeacherOperations {
	final StudentTrackerRestClient client;
	final Map<Long, JournalMaster> journalMasterDictionary;

	public TeacherOperations(StudentTrackerRestClient client, Map<Long, JournalMaster> journalMasterDictionary) {
		this.client = client;
		this.journalMasterDictionary = journalMasterDictionary;
	}

	@SuppressWarnings("resource")
	public Teacher login() throws IOException {
		Console cnsl= System.console();
		Scanner myObj = new Scanner(System.in).useDelimiter("\n");
		System.out.print("Teacher User Id: ");
		String username = myObj.next();
		//System.out.print("Teacher Password: ");
		//String password = myObj.next();
		char[] password = cnsl.readPassword("Teacher Password: ");
		Teacher teacher = client.searchTeacher(username, String.valueOf(password));
		return teacher;
	}

	// TODO revisit ordering
	@SuppressWarnings("resource")
	public void perform(Teacher teacher) throws IOException, ParseException {
		System.out.println("Hello, welcome to your teacher portal.");
		Scanner myObj = new Scanner(System.in).useDelimiter("\n");
		boolean inAccount = true;
		while (inAccount) {
			System.out.println("Please choose the following options: \n " 
					+ "1.  vas - view all students \n"
					+ "2.  vc  - view classrooms \n" 
					+ "3.  cc  - create classroom \n"
					+ "4.  e   - enroll students into classroom \n" 
					+ "5.  vcs - view classroom students \n"
					+ "6.  c   - create journal assignments \n" 
					+ "7.  a   - assign journals  \n"
					+ "8.  vca - view created assignments \n"
					+ "9.  vs  - view incomplete submissions for journal assignments \n"
					+ "10. vus - view ungraded submissions \n" 
					+ "11. gf  - give feedback \n" 
					+ "12. q   - quit): ");
			System.out.print("Enter your choice: ");
			String option = myObj.nextLine();
			if (option.equals("vas")) {
				viewAllStudents();
			} else if (option.equals("vc")) {
				viewClassrooms();
			} else if (option.equals("cc")) {
				createClassroom(teacher);
			} else if (option.equals("e")) {
				enrollStudentIntoClassroom();
			} else if (option.equals("vcs")) {
				viewClassroomStudents();
			} else if (option.equals("c")) {
				createJournalAssignments();
			} else if (option.equals("a")) {
				assignJournal();
			} else if (option.equals("vca")) {
				viewcreatedAssignments();
			} else if (option.equals("gf")) {
				giveFeedback();
			} else if (option.equals("vs")) {
				viewIncompleteSubmissions();
			} else if (option.equals("vus")) {
				viewUngradedSubmissions();
			} else if (option.equals("q")) {
				System.out.println("Logged out!");
				inAccount = false;
			} else {
				System.out.println("Invalid option!");
			}
		}
	}

	private void viewUngradedSubmissions() throws IOException {
		long journalId = UserInputUtil.getLongNumber("Journal ID");
		List<Journal> ungradedJournals = client.findUngradedAssignments(journalId);
		final List<UngradedSubmissions> ungradedSubmissions = new ArrayList<UngradedSubmissions>();
		for (Journal ungradedJournal : ungradedJournals) {
			final JournalMaster journalMaster = journalMasterDictionary.get(ungradedJournal.getId().getJournalId());
			final String prompt = journalMaster.getPrompt();
			ungradedSubmissions.add(UngradedSubmissions.create(ungradedJournal, prompt));
		}
		if (ungradedSubmissions.size() > 0) {
			DisplayUtil.render(ungradedSubmissions);
		} else {
			System.out.println("No ungraded submissions");
		}
	}

	private void viewClassrooms() throws IOException {
		List<Classroom> classrooms = client.findAllClassrooms();
		DisplayUtil.render(classrooms);
	}

	private void viewAllStudents() throws IOException {
		List<Student> students = client.findAllStudents();
		DisplayUtil.render(students);
	}

	@SuppressWarnings("resource")
	private void createClassroom(Teacher teacher) throws IOException {
		Scanner myObj = new Scanner(System.in).useDelimiter("\n");
		System.out.print("Enter class name: ");
		String className = myObj.next();
		Classroom classroom = new Classroom(teacher.getId(), className);
		client.createClassroom(classroom);
	}

	@SuppressWarnings("resource")
	private void enrollStudentIntoClassroom() throws IOException {
		long studentId = UserInputUtil.getLongNumber("Student ID");
		long classId = UserInputUtil.getLongNumber("Class ID");
		ClassEnrollmentId classEnrollmentId = new ClassEnrollmentId(studentId, classId);
		ClassEnrollment classEnrollment = new ClassEnrollment(classEnrollmentId);
		client.enrollStudent(classEnrollment);
	}

	private void viewClassroomStudents() throws IOException {
		long classId = UserInputUtil.getLongNumber("Class ID");
		List<Student> students = client.getStudentsFromClassroom(classId);
		DisplayUtil.render(students);
	}

	private void viewIncompleteSubmissions() throws IOException {
		long journalId = UserInputUtil.getLongNumber("Journal ID");
		final List<Journal> journals = client.findIncompleteJournals(journalId);
		final List<IncompleteSubmissions> incompleteSubmissions = new ArrayList<IncompleteSubmissions>();
		for (Journal journal : journals) {
			incompleteSubmissions.add(IncompleteSubmissions.create(journal));
		}
		if (incompleteSubmissions.size() > 0) {
			DisplayUtil.render(incompleteSubmissions);
		} else {
			System.out.println("No incomplete submissions");
		}
	}

	@SuppressWarnings("resource")
	private void giveFeedback() throws IOException {
		Scanner myObj = new Scanner(System.in).useDelimiter("\n");
		long journalId = UserInputUtil.getLongNumber("Journal ID");
		long studentId = UserInputUtil.getLongNumber("Student ID");
		System.out.print("Feedback text: ");
		String feedback = myObj.next();
		System.out.print("Points earned: ");
		int pointsEarned = myObj.nextInt();
		JournalId id = new JournalId(journalId, studentId);
		Journal journal = new Journal();
		journal.setId(id);
		journal.setFeedbackText(feedback);
		journal.setEarnedPoints(pointsEarned);
		client.giveFeedback(journal);
	}

	@SuppressWarnings("resource")
	private void viewcreatedAssignments() {
		long classId = UserInputUtil.getLongNumber("Class ID");
		List<JournalMaster> journalMasters = new ArrayList<JournalMaster>();
		for (JournalMaster journalMaster : journalMasterDictionary.values()) {
			if (journalMaster.getClassId() == classId) {
				journalMasters.add(journalMaster);
			}
		}
		DisplayUtil.render(journalMasters);
	}

	@SuppressWarnings("resource")
	private void createJournalAssignments() throws ParseException, IOException {
		Scanner myObj = new Scanner(System.in).useDelimiter("\n");
		long classId = UserInputUtil.getLongNumber("Class ID");
		System.out.print("Enter Prompt: ");
		String prompt = myObj.next();
		int maxPoints = UserInputUtil.getIntNumber("Maximum Points");
		JournalMaster journalMaster = new JournalMaster(classId, prompt, maxPoints);
		client.createJournal(journalMaster);
	}

	@SuppressWarnings("resource")
	private void assignJournal() throws IOException {
		Scanner myObj = new Scanner(System.in);
		long journalId = UserInputUtil.getLongNumber("Journal ID");
		System.out.print("Enter the # of days in which the assignment should be submitted");
		int days = myObj.nextInt();
		JournalMaster journalMaster = new JournalMaster();
		journalMaster.setId(journalId);
		journalMaster.setDueDaysFromToday(days);
		int numberOfAssignments = client.assignJournal(journalMaster);
		System.out.println(numberOfAssignments + " new assignments were created!");
	}
}