package qls.music.studenttracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qls.music.studenttracker.model.Teacher;
import qls.music.studenttracker.repository.TeacherRepository;

@Service
public class TeacherService {
	@Autowired
    TeacherRepository teacherRepository;
	
	public boolean existsByEmailIdAndPassword(String emailId, String password) {
		return teacherRepository.existsByEmailIdAndPassword(emailId, password);
	}
	
	public Teacher findByEmailIdAndPassword(String emailId, String password) {
		return teacherRepository.findByEmailIdAndPassword(emailId, password);
	}
}
