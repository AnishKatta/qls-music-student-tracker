package qls.music.studenttracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qls.music.studenttracker.model.Student;
import qls.music.studenttracker.repository.StudentRepository;

@Service
public class StudentService {

	 @Autowired
     StudentRepository studentRepository;
	 
	 @Autowired
	 ClassEnrollmentService classEnrollmentService;


	 public boolean existsByEmailIdAndPassword(String emailId, String password) {
	     return studentRepository.existsByEmailIdAndPassword(emailId, password);
	 }
	 
	 public Student findByEmailIdAndPassword(String emailId, String password) {
		 return studentRepository.findByEmailIdAndPassword(emailId, password);
	 };
	 
	 public List<Student> findAllStudents(){
		 return studentRepository.findAll();
	 }
	 
	 public List<Student> findAllById(Long classId){
		 List<Long> studentIds = classEnrollmentService.findStudents(classId);
		 return studentRepository.findAllById(studentIds);
	 }
	 
	 public boolean studentExists(Long id) {
		 return studentRepository.existsById(id);
	 }
	 
	 public Student findById(Long id) {
		 return studentRepository.findById(id).get();
	 }
}
