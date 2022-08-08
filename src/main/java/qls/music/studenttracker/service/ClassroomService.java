package qls.music.studenttracker.service;

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
}
