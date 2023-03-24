package qls.music.studenttracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qls.music.studenttracker.model.ClassEnrollment;
import qls.music.studenttracker.service.ClassEnrollmentService;

@RestController
@RequestMapping("/api")
public class ClassEnrollmentController {
	@Autowired
	ClassEnrollmentService classEnrollmentService;
	
	@RequestMapping(value="/enrollStudent", method=RequestMethod.POST)
    public ClassEnrollment enrollStudent(@RequestBody ClassEnrollment classEnrollment) {
        return classEnrollmentService.enrollStudent(classEnrollment);
    }
	
	@RequestMapping(value="/findStudents", method=RequestMethod.GET)
    public List<Long> findStudents(@RequestParam(name = "class_id") Long classId) {
        return classEnrollmentService.findStudents(classId);
    }
	
	@RequestMapping(value="/studentEnrolled", method=RequestMethod.GET)
    public boolean studentEnrolled(@RequestParam(name = "student_id") Long studentId) {
        return classEnrollmentService.studentEnrolled(studentId);
    }
	
	
}
