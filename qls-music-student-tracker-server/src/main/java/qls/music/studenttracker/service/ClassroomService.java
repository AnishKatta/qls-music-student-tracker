package qls.music.studenttracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qls.music.studenttracker.model.Classroom;
import qls.music.studenttracker.repository.ClassroomRepository;

@Service
public class ClassroomService {
	@Autowired
    ClassroomRepository classroomRepository;
	
	public Classroom createClassroom(Classroom classroom) {
		return classroomRepository.save(classroom);
	}
	
	public List<Classroom> findAllClassrooms(){
		return classroomRepository.findAll();
	}
	
	public boolean classroomExists(Long classId) {
		return classroomRepository.existsById(classId);
	}
	
	public Classroom findById(Long classId) {
		return classroomRepository.findById(classId).get();
	}
}
