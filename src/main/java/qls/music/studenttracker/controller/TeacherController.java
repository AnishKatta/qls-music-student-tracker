package qls.music.studenttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
