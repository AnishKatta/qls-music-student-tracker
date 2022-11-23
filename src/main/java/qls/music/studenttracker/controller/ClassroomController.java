package qls.music.studenttracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import qls.music.studenttracker.model.Classroom;
import qls.music.studenttracker.service.ClassroomService;

@RestController
@RequestMapping("/api")
public class ClassroomController {
	@Autowired
	ClassroomService classroomService;
	
	@RequestMapping(value="/createClassroom", method=RequestMethod.POST)
	public Classroom createClassroom(@RequestBody Classroom classroom) {
		//System.out.println("hi");
	    return classroomService.createClassroom(classroom);
	}
	
	@RequestMapping(value="/findAllClassrooms", method=RequestMethod.GET)
	public List<Classroom> findAllClassrooms() {
	    return classroomService.findAllClassrooms();
	}
	
	
}
