package qls.music.studenttracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qls.music.studenttracker.model.Student;
import qls.music.studenttracker.service.ClassEnrollmentService;
import qls.music.studenttracker.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	StudentService studentService;

	ClassEnrollmentService classEnrollmentService;

	@RequestMapping(value = "/studentLogin", method = RequestMethod.GET)
	public Student searchStudent(@RequestParam(name = "email_id") String emailId,
			@RequestParam(name = "password") String password) {
		final boolean studentFound = studentService.existsByEmailIdAndPassword(emailId, password);
		if (studentFound) {
			Student student = studentService.findByEmailIdAndPassword(emailId, password);
			return student;
		}
		return new Student();
	}

	@RequestMapping(value = "/getStudent", method = RequestMethod.GET)
	public Student getStudent(@RequestParam(name = "email_id") String emailId,
			@RequestParam(name = "password") String password) {
		Student student = studentService.findByEmailIdAndPassword(emailId, password);
		return student;
	}
	
	@RequestMapping(value = "/findStudentById", method = RequestMethod.GET)
	public Student findStudentById(@RequestParam(name = "id") Long id) {
		Student student = studentService.findById(id);
		return student;
	}
	

	@RequestMapping(value = "/findAllStudents", method = RequestMethod.GET)
	public List<Student> findAllStudents() {
		return studentService.findAllStudents();
	}

	@RequestMapping(value = "/getStudentsFromClassroom", method = RequestMethod.GET)
	public List<Student> getStudentsFromClassroom(@RequestParam(name = "class_id") Long classId) {
		return studentService.findAllById(classId);
	}
	
	@RequestMapping(value = "/studentExists", method = RequestMethod.GET)
	public boolean studentExists(@RequestParam(name = "id") Long id) {
		return studentService.studentExists(id);
	}
	
	
	
	
}