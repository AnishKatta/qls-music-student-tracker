package qls.music.studenttracker.rest.client;

import java.io.IOException;
import java.util.List;

import qls.music.studenttracker.client.model.ClassEnrollment;
import qls.music.studenttracker.client.model.Classroom;
import qls.music.studenttracker.client.model.Journal;
import qls.music.studenttracker.client.model.JournalMaster;
import qls.music.studenttracker.client.model.Student;
import qls.music.studenttracker.client.model.Teacher;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class StudentTrackerRestClient {

	private final StudentTrackerRestContract contract;

	public StudentTrackerRestClient(final String baseUrl) {
		final Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
				.addConverterFactory(JacksonConverterFactory.create()).build();
		this.contract = retrofit.create(StudentTrackerRestContract.class);
	}
	
	public List<Journal> getCompletedJournalsForStudent(final Long studentId) throws IOException {
		return contract.getCompletedJournalsForStudent(String.valueOf(studentId)).execute().body();
	}
	
	public List<Journal> getIncompleteJournalsForStudent(final Long studentId) throws IOException {
		return contract.getIncompleteJournalsForStudent(String.valueOf(studentId)).execute().body();
	}
	
	public List<Journal> findIncompleteJournals(final Long journalId) throws IOException {
		return contract.findIncompleteJournals(journalId).execute().body();
	}
	
	public List<Journal> findUngradedAssignments(final Long journalId) throws IOException {
		return contract.findUngradedAssignments(journalId).execute().body();
	}
	
	public Journal submitAssignment(Journal journal) throws IOException {
		return contract.submitAssignment(journal).execute().body();

	}
	
	public Journal findJournal(final Long journalId, final Long studentId) throws IOException {
		return contract.findJournal(String.valueOf(journalId), String.valueOf(studentId)).execute().body();

	}
	
	public List<JournalMaster> getAllJournalMaster() throws IOException{
		return contract.getAllJournalMaster().execute().body();
	}
	
	public Student studentLogin(String emailId, String password) throws IOException {
		return contract.studentLogin(emailId, password).execute().body();
	}
	
	public List<Student> findAllStudents() throws IOException{
		return contract.findAllStudents().execute().body();
	}
	
	public Student findStudent(Long id) throws IOException{
		return contract.findStudentById(id).execute().body();
	}
	
	public List<Student> getStudentsFromClassroom(Long classId) throws IOException{
		return contract.getStudentsFromClassroom(classId).execute().body();
	}
	
	public boolean classroomExists(Long classId) throws IOException {
		return contract.classroomExists(classId).execute().body();
	}
	public boolean studentEnrolled(Long studentId) throws IOException {
		return contract.studentEnrolled(studentId).execute().body();
	}
	
	public boolean studentExists(Long id) throws IOException {
		return contract.studentExists(id).execute().body();
	}
	

	

	/**
	public Student getStudent(String emailId, String password) throws IOException {
		return contract.getStudent(emailId, password).execute().body();

	}
	
	**/
	
	public Teacher searchTeacher(String emailId, String password) throws IOException {
		return contract.searchTeacher(emailId, password).execute().body();

	}
	
	public List<Classroom> findAllClassrooms() throws IOException{
		return contract.findAllClassrooms().execute().body();
	}
	
	public Classroom findClassroom(Long id) throws IOException{
		return contract.findClassroomById(id).execute().body();
	}
	
	public Classroom createClassroom(Classroom classroom) throws IOException{
		return contract.createClassroom(classroom).execute().body();
	}
	
	public ClassEnrollment enrollStudent(ClassEnrollment classEnrollment) throws IOException {
		return contract.enrollStudent(classEnrollment).execute().body();
	}
	
	public JournalMaster createJournal(JournalMaster journalMaster) throws IOException {
		return contract.createJournal(journalMaster).execute().body();
	}
	
	public int assignJournal(JournalMaster journalMaster) throws IOException {
		return contract.assignJournal(journalMaster).execute().body();
	}
	
	public boolean journalMasterExists(Long id) throws IOException {
		return contract.journalMasterExists(id).execute().body();
	}
	
	public boolean journalExists(Long journalId) throws IOException {
		return contract.journalExists(journalId).execute().body();
	}
	
	public Journal giveFeedback(Journal journal) throws IOException {
		return contract.giveFeedback(journal).execute().body();
	}
	
	public boolean validFeedback(Long journalId, Long studentId) throws IOException {
		return contract.validFeedback(journalId, studentId).execute().body();
	}
	
	
	

	
	
}
