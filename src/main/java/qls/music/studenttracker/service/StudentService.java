package qls.music.studenttracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qls.music.studenttracker.model.Student;
import qls.music.studenttracker.repository.StudentRepository;

@Service
public class StudentService {

	 @Autowired
     StudentRepository studentRepository;
	 
	/**CREATE 
	 public Employee createEmployee(Employee emp) {
	     return empRepository.save(emp);
	 }
	 **/

	 // READ
	 public boolean existsByEmailIdAndPassword(String emailId, String password) {
	     return studentRepository.existsByEmailIdAndPassword(emailId, password);
	 }
	 
	 public Student findByEmailIdAndPassword(String emailId, String password) {
		 return studentRepository.findByEmailIdAndPassword(emailId, password);
	 };

	 /** DELETE
	 public void deleteEmployee(Long empId) {
	     empRepository.deleteById(empId);
	 }
	 **/
	 
	/** UPDATE
	 public Employee updateEmployee(Long empId, Employee employeeDetails) {
	         Employee emp = empRepository.findById(empId).get();
	         emp.setFirstName(employeeDetails.getFirstName());
	         emp.setLastName(employeeDetails.getLastName());
	         emp.setEmailId(employeeDetails.getEmailId());
	         
	         return empRepository.save(emp);                                
	 }
	 **/

}
