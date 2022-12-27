package qls.music.studenttracker.rest.client;

import java.util.List;


import qls.music.studenttracker.client.model.ClassEnrollment;
import qls.music.studenttracker.client.model.Classroom;
import qls.music.studenttracker.client.model.Journal;
import qls.music.studenttracker.client.model.JournalMaster;
import qls.music.studenttracker.client.model.Student;
import qls.music.studenttracker.client.model.Teacher;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface StudentTrackerRestContract {
	
	@GET("/api/getCompletedJournalsForStudent")
	Call<List<Journal>> getCompletedJournalsForStudent(
			@Query("student_id") final String studentId
			);
	
	@GET("/api/getIncompleteJournalsForStudent")
	Call<List<Journal>> getIncompleteJournalsForStudent(
			@Query("student_id") final String studentId
			);
	
	@GET("/api/findIncompleteJournals")
	Call<List<Journal>> findIncompleteJournals(
			@Query("journal_id") final Long journalId
			);
	
	@GET("/api/findUngradedAssignments")
	Call<List<Journal>> findUngradedAssignments(
			@Query("journal_id") final Long journalId
			);
	
	@GET("/api/studentLogin")
	Call<Student> studentLogin(
			@Query("email_id") final String emailId, 
			@Query("password")final String password
			);
	
	@GET("/api/findAllStudents")
	Call<List<Student>> findAllStudents();
	
/**
	@GET("/api/getStudent")
	Call<Student> getStudent(
			@Query("email_id") final String emailId, @Query("password")final String password
			);
**/	
	@GET("/api/searchTeacher")
	Call<Teacher> searchTeacher(
			@Query("email_id") final String emailId, 
			@Query("password")final String password
			);
	
	@PUT("/api/submitAssignment")
	Call<Journal> submitAssignment(
			@Body Journal journal 
			);
	
	@GET("/api/findJournal")
	Call<Journal> findJournal(
			@Query("journal_id") final String journalId, 
			@Query("student_id") final String studentId  
			);
	
	
	@GET("/api/getAllJournalMaster")
	Call<List<JournalMaster>> getAllJournalMaster(
			);
	
	
	@POST("/api/createJournal")
	Call<JournalMaster> createJournal(
			@Body JournalMaster journalMaster
			);
	
	@POST("/api/assignJournal")
	Call<Integer> assignJournal(
			@Body JournalMaster journalMaster
			);

	
	@GET("/api/findAllClassrooms")
	Call<List<Classroom>> findAllClassrooms();
	
	
	@POST("/api/createClassroom")
	Call<Classroom> createClassroom(
			@Body Classroom classroom 
			);
	
	
	@POST("/api/enrollStudent")
	Call<ClassEnrollment> enrollStudent(
			@Body ClassEnrollment classEnrollment 
			);
	
	
	@GET("/api/getStudentsFromClassroom")
	Call<List<Student>> getStudentsFromClassroom(
			@Query("class_id") final Long classId
			);
	
	@PUT("/api/giveFeedback")
	Call<Journal> giveFeedback(
			@Body Journal journal
			);
}