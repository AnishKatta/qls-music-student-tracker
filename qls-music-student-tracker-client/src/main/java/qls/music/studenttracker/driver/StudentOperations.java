package qls.music.studenttracker.driver;

import java.io.Console;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import qls.music.studenttracker.client.model.CompletedAssignment;
import qls.music.studenttracker.client.model.IncompleteAssignment;
import qls.music.studenttracker.client.model.Journal;
import qls.music.studenttracker.client.model.JournalId;
import qls.music.studenttracker.client.model.JournalMaster;
import qls.music.studenttracker.client.model.Student;
import qls.music.studenttracker.rest.client.StudentTrackerRestClient;

public class StudentOperations {
	final StudentTrackerRestClient client;
	final Map<Long, JournalMaster> journalMasterDictionary;

	public StudentOperations(StudentTrackerRestClient client, Map<Long, JournalMaster> journalMasterDictionary) {
		this.client = client;
		this.journalMasterDictionary = journalMasterDictionary;
	}

	@SuppressWarnings("resource")
	public Student login() throws IOException {
		Console cnsl = System.console();
		Scanner myObj = new Scanner(System.in).useDelimiter("\n");
		System.out.print("Student User Id: ");
		String username = myObj.next();
		char[] password = cnsl.readPassword("Student Password: ");
		Student student = client.studentLogin(username, String.valueOf(password));
		return student;
	}


	@SuppressWarnings("resource")
	public void perform(final Student student) throws IOException {
		System.out.println("\nHello " + student.getName() + ", welcome to your student portal.");
		Scanner myObj = new Scanner(System.in).useDelimiter("\n");
		boolean inAccount = true;
		while (inAccount) {
			System.out.println();
			System.out.println("Please choose the following options: \n" 
					+ "1. via - view incomplete assignments \n"
					+ "2. vca - view completed assignments \n" 
					+ "3. sa  - submit assignment \n" 
					+ "4. q   - quit");
			System.out.print("Enter your choice: ");
			String option = myObj.next();
			if (option.equals("vca")) {
				viewCompletedAssignments(student);
			} else if (option.equals("via")) {
				viewIncompleteAssignments(student);
			} else if (option.equals("sa")) {
				submitAssignment(student);
			} else if (option.equals("q")) {
				System.out.println("Logged out!");
				inAccount = false;
			} else {
				System.out.println("Invalid option chosen!");
			}
		}
	}

	/**
	 * students can view their completed assignments
	 * 
	 */
	
	private void viewCompletedAssignments(final Student student) throws IOException {
		final List<Journal> journals = client.getCompletedJournalsForStudent(student.getId());
		final List<CompletedAssignment> completedJournals = new ArrayList<CompletedAssignment>();
		for (Journal journal : journals) {
			long id = journal.getId().getJournalId();
			JournalMaster journalMaster = journalMasterDictionary.get(id);
			journal.setPrompt(journalMaster.getPrompt());
			journal.setMaxPoints(journalMaster.getMaxPoints());
			completedJournals.add(CompletedAssignment.create(journal));
		}
		if (completedJournals.size() > 0) {
			DisplayUtil.render(completedJournals);
		} else {
			System.out.println("No completed assignments");
		}
	}

	/**
	 * students can view their incomplete assignments
	 * 
	 */
	private void viewIncompleteAssignments(final Student student) throws IOException {
		final List<Journal> journals = client.getIncompleteJournalsForStudent(student.getId());
		if(!journals.isEmpty()) {
			final List<IncompleteAssignment> incompleteJournals = new ArrayList<IncompleteAssignment>();
			for (Journal journal : journals) {
				long id = journal.getId().getJournalId();
				JournalMaster journalMaster = journalMasterDictionary.get(id);
				if(journalMaster != null) {
					journal.setPrompt(journalMaster.getPrompt());
					journal.setMaxPoints(journalMaster.getMaxPoints());
					journal.setDueDate(journalMaster.getDueDate());
					incompleteJournals.add(IncompleteAssignment.create(journal));
				}
			}
			if (incompleteJournals.size() > 0) {
				DisplayUtil.render(incompleteJournals);
			} else {
				System.out.println("No incomplete assignments");
			}
		}else {
			System.out.println("No incomplete assignments");
		}
		
	}

	/**
	 * students can submit journal assignment
	 * 
	 */
	@SuppressWarnings("resource")
	private void submitAssignment(final Student student) throws IOException {
		final List<Journal> journals = client.getIncompleteJournalsForStudent(student.getId());
		if(journals.size() == 0) {
			System.out.println("All assignments are completed");
		}
		else {
			Scanner myObj = new Scanner(System.in).useDelimiter("\n");
			long journalId = UserInputUtil.getLongNumber("Journal ID");
			Journal journalFromDB = client.findJournal(journalId, student.getId());
			if (isValidJournalForSubmission(journalFromDB)) {// put journalFromDB into method that does validation
				System.out.print("Enter journal text:  ");
				String text = myObj.next();
				JournalId id = new JournalId(journalId, student.getId());
				Journal journal = new Journal(id, text);
				client.submitAssignment(journal);
				System.out.println("Submission Done!");
			}
		}
		
	}

	/**
	 * checks if input for journal id is valid(valid integer and journal id)
	 *
	 */
	private boolean isValidJournalForSubmission(final Journal journal) {
		if (journal.getId() == null) {
			System.out.println("Invalid Journal ID!");
			return false;
		}
		if (journal.getText() != null) {
			System.out.println("Already submitted assignment!");
			return false;
		}
		return true;
	}
}
