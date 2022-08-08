package qls.music.studenttracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qls.music.studenttracker.model.ClassEnrollment;
import qls.music.studenttracker.model.ClassEnrollmentId;
import qls.music.studenttracker.repository.ClassEnrollmentRepository;

@Service
public class ClassEnrollmentService {
	@Autowired
	ClassEnrollmentRepository classEnrollmentRepository;
	
	 public ClassEnrollment enrollStudent(ClassEnrollment classEnrollment) {
		 return classEnrollmentRepository.save(classEnrollment);
	 }

	 public List<Long> findStudents(Long classId) {
		 List<Long> studentIds = new ArrayList<Long>();
		 
		 List<ClassEnrollment> classEnrollments = classEnrollmentRepository.findByIdClassId(classId);
		 for(ClassEnrollment temp : classEnrollments) {
			  studentIds.add(temp.getId().getStudentId());
		 }
		 return studentIds;
	 }
}
