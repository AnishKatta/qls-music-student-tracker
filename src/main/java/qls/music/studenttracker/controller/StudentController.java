package qls.music.studenttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qls.music.studenttracker.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {
	
    @Autowired
    StudentService studentService;
    
    
    @RequestMapping(value="/searchStudent", method=RequestMethod.GET)
    public boolean searchStudent(@RequestParam(name = "email_id") String emailId, 
			@RequestParam(name = "password") String password) {
        return studentService.existsByEmailIdAndPassword(emailId, password);
    }
}
