package qls.music.studenttracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qls.music.studenttracker.model.Teacher;
import qls.music.studenttracker.service.TeacherService;

@RestController
@RequestMapping("/api")
public class TeacherController {
	@Autowired
    TeacherService teacherService;
	
	@RequestMapping(value="/searchTeacher", method=RequestMethod.GET)
	public boolean existsByEmailIdAndPassword(@RequestParam(name = "email_id") String emailId, 
			@RequestParam(name = "password") String password) {
		return teacherService.existsByEmailIdAndPassword(emailId, password);
	}
}
