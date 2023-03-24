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
		char[] password = cnsl.readPassword("Teacher Password: ");
		Teacher teacher = client.searchTeacher(username, String.valueOf(password));
		return teacher;
	}

	
	@SuppressWarnings("resource")
	public void perform(Teacher teacher) throws IOException, ParseException {
		System.out.println("\nHello, welcome to your teacher portal.");
		Scanner myObj = new Scanner(System.in).useDelimiter("\n");
		boolean inAccount = true;
		while (inAccount) {
			System.out.println();
			System.out.println("Please choose the following options:\n" 
					+ "1.  vas - view all students \n"
					+ "2.  vc  - view classrooms \n" 
					+ "3.  cc  - create classroom \n"
					+ "4.  e   - enroll students into classroom \n" 
					+ "5.  vcs - view classroom students \n"
					+ "6.  c   - create journal assignments \n" 
					+ "7.  a   - assign journals  \n"
					+ "8.  vca - view created assignments \n"
					+ "9.  vis  - view incomplete submissions for journal assignments \n"
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
			} else if (option.equals("vis")) {
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
	
	//done
	private void viewUngradedSubmissions() throws IOException {
		long journalId = UserInputUtil.getLongNumber("Journal ID");
		if(client.journalMasterExists(journalId)) {
			if(client.journalExists(journalId)) {
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
			}else {
				System.out.println("Journal not assigned yet!");
			}
		}else {
			System.out.println("Invalid journal id!");
		}
		
	}
	
	//done
	private void viewClassrooms() throws IOException {
		List<Classroom> classrooms = client.findAllClassrooms();
		DisplayUtil.render(classrooms);
	}
	
	
	//done
	private void viewAllStudents() throws IOException {
		List<Student> students = client.findAllStudents();
		DisplayUtil.render(students);
	}
	
	
	//done
	@SuppressWarnings("resource")
	private void createClassroom(Teacher teacher) throws IOException {
		Scanner myObj = new Scanner(System.in).useDelimiter("\n");
		System.out.print("Enter class name: ");
		String className = myObj.next();
		Classroom classroom = new Classroom(teacher.getId(), className);
		client.createClassroom(classroom);
		System.out.println(String.format("%s successfully created ", className));
	}
	
	//done
	@SuppressWarnings("resource")
	private void enrollStudentIntoClassroom() throws IOException {
		long studentId = UserInputUtil.getLongNumber("Student ID");
		long classId = UserInputUtil.getLongNumber("Class ID");
		if(isValidStudentEnrollment(classId, studentId)) {
			ClassEnrollmentId classEnrollmentId = new ClassEnrollmentId(studentId, classId);
			ClassEnrollment classEnrollment = new ClassEnrollment(classEnrollmentId);
			client.enrollStudent(classEnrollment);
			System.out.println(String.format("%s successfully enrolled to class %s ", 
					client.findStudent(studentId).getName(), 
					client.findClassroom(classId).getName()
			));

		}else {
			System.out.println("Student already enrolled in class!");
		}
		
	}
	
	//done
	private void viewClassroomStudents() throws IOException {
		long classId = UserInputUtil.getLongNumber("Class ID");
		if(isValidClassroom(classId)) {
			List<Student> students = client.getStudentsFromClassroom(classId);
			if(students.isEmpty()) {
				System.out.println("No students have been assigned to this class!");
			}else {
				DisplayUtil.render(students);
			}
		}else {
			System.out.println("Invalid classroom!");
		}
		
	}
	
	
	//done
	private void viewIncompleteSubmissions() throws IOException {
		long journalId = UserInputUtil.getLongNumber("Journal ID");
		if(client.journalMasterExists(journalId)) {
			if(client.journalExists(journalId)) {
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
			}else {
				System.out.println("Journal not assigned yet!");
			}
		}else {
			System.out.println("Invalid journal id!");
		}
		
	}

	//done
	@SuppressWarnings("resource")
	private void giveFeedback() throws IOException {
		Scanner myObj = new Scanner(System.in).useDelimiter("\n");
		long journalId = UserInputUtil.getLongNumber("Journal ID");
		long studentId = UserInputUtil.getLongNumber("Student ID");
		if(client.validFeedback(journalId, studentId)) {
			if(client.findJournal(journalId, studentId).getFeedbackText() == null) {
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
			}else {
				System.out.println("Feedback already given!");
			}
		}else {
			System.out.println("Feedback can't be given!");
		}
		
	}

	
	//done
	@SuppressWarnings("resource")
	private void viewcreatedAssignments() throws IOException {
		long classId = UserInputUtil.getLongNumber("Class ID");
		if(isValidClassroom(classId)) {
			List<JournalMaster> journalMasters = client.getAllJournalMaster();
			List<JournalMaster> classJournalMasters = new ArrayList<JournalMaster>();
			for (JournalMaster journalMaster : journalMasters) {
				if (journalMaster.getClassId() == classId) {
					classJournalMasters.add(journalMaster);
				}
			}
			if(!classJournalMasters.isEmpty()) {
				DisplayUtil.render(classJournalMasters);	
			} else {
				System.out.println("There are no created assignments for this class!");
			}
		}else {
			System.out.println("Invalid classroom!");
		}
		
	}

	
	//done
	@SuppressWarnings("resource")
	private void createJournalAssignments() throws ParseException, IOException {
		Scanner myObj = new Scanner(System.in).useDelimiter("\n");
		long classId = UserInputUtil.getLongNumber("Class ID");
		if(isValidClassroom(classId)) {
			System.out.print("Enter Prompt: ");
			String prompt = myObj.next();
			int maxPoints = UserInputUtil.getIntNumber("Maximum Points");
			JournalMaster journalMaster = new JournalMaster(classId, prompt, maxPoints);
			client.createJournal(journalMaster);
		}else {
			System.out.println("Invalid classroom");
		}
	
	}

	//done
	@SuppressWarnings("resource")
	private void assignJournal() throws IOException {
		Scanner myObj = new Scanner(System.in);
		long journalId = UserInputUtil.getLongNumber("Journal ID");
		if(client.journalMasterExists(journalId)) {
			if(!client.journalExists(journalId)){
				System.out.print("Enter the # of days in which the assignment should be submitted");
				int days = myObj.nextInt();
				JournalMaster journalMaster = new JournalMaster();
				journalMaster.setId(journalId);
				journalMaster.setDueDaysFromToday(days);
				int numberOfAssignments = client.assignJournal(journalMaster);
				System.out.println(numberOfAssignments + " new assignments were created!");
			}else {
				System.out.println("Journal already assigned!");
			}
		}else {
			System.out.println("Invalid journal!");
		}
			
	
	
	}
	
	private boolean isValidStudentEnrollment(Long classId, Long studentId) throws IOException {
		if(client.studentExists(studentId) && client.classroomExists(classId)) {
			if(!client.studentEnrolled(studentId)) {
				return true;
			}
		}
		return false;
		
	}
	
	private boolean isValidClassroom(Long classId) throws IOException {
		if(client.classroomExists(classId)) {
			return true;
		}
		return false;
	}
}