package qls.music.studenttracker.driver;

import java.io.IOException;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import qls.music.studenttracker.client.model.JournalMaster;
import qls.music.studenttracker.client.model.Student;
import qls.music.studenttracker.client.model.Teacher;
import qls.music.studenttracker.rest.client.StudentTrackerRestClient;

public class Driver {

	final Map<Long, JournalMaster> journalMasterDictionary = new HashMap<Long, JournalMaster>();
	final StudentTrackerRestClient client = new StudentTrackerRestClient("http://localhost:8080");

	@SuppressWarnings("resource")
	public static void main(String args[]) throws IOException, ParseException {
		final StudentOperations studentOperations;
		final TeacherOperations teacherOperations;
		System.out.println("Welcome to the music tracker system!");
		Scanner myObj = new Scanner(System.in).useDelimiter("\n");
		Driver driver = new Driver();
		driver.loadCache();
		studentOperations = new StudentOperations(driver.client, driver.journalMasterDictionary);
		teacherOperations = new TeacherOperations(driver.client, driver.journalMasterDictionary);
		while (true) {
			System.out.println("Please choose the following options: \n"
					+ " s - student \n"
					+ " t - teacher \n"
					+ " q - quit: ");
			System.out.print("Enter your choice: ");
			String user = myObj.next();
			if (user.equals("s")) {
				Student student = studentOperations.login();
				if (student != null && student.isValidStudent()) {
					studentOperations.perform(student);
				}
				System.out.println("Incorrect login information!");
			} else if (user.equals("t")) {
				Teacher teacher = teacherOperations.login();
				if (teacher != null && teacher.isValidTeacher()) {
					teacherOperations.perform(teacher);
				}
				System.out.println("Incorrect login information!");
			} else if (user.equals("q")) {
				System.out.println("Program terminated!");
				System.exit(0);
			} else {
				System.out.println("Invalid option chosen!");
			}
		}
	}

	private void loadCache() throws IOException {
		final List<JournalMaster> journalMasterList = client.getAllJournalMaster();
		for (JournalMaster journalMaster : journalMasterList) {
			journalMasterDictionary.put(journalMaster.getId(), journalMaster);
		}
	}

}
