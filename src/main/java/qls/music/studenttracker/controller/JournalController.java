package qls.music.studenttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qls.music.studenttracker.model.JournalMaster;
import qls.music.studenttracker.service.JournalService;

@RestController
@RequestMapping("/api")
public class JournalController {
	@Autowired
	JournalService journalService;
	
	@RequestMapping(value="/assignJournal", method=RequestMethod.POST)
    public int assignJournal(@RequestBody JournalMaster journalMaster) throws Exception {
		return journalService.assignJournalToStudents(journalMaster);
    }
	
//	@RequestMapping(value="/getJournalsForStudent", method=RequestMethod.GET)
//    public List<Journal> getJournalsForStudent(@RequestParam(name = "student_id") Long student_id) {
//		return journalService.getJournalsForStudent(student_id);
//    }
	
	@RequestMapping(value="/getCompletedJournalsForStudent", method=RequestMethod.GET)
    public int getCompletedJournalsForStudent(@RequestParam(name = "student_id") Long student_id) {
		return journalService.getCompletedJournalsForStudent(student_id);
    }
	
	@RequestMapping(value="/getIncompleteJournalsForStudent", method=RequestMethod.GET)
    public int getIncompleteJournalsForStudent(@RequestParam(name = "student_id") Long student_id) {
		return journalService.getIncompleteJournalsForStudent(student_id);
    }
	
	@RequestMapping(value="/submitAssignment", method=RequestMethod.PUT)
    public void submitAssignment(@RequestParam(name = "text") String text, 
    		@RequestParam(name = "journal_id") Long journalId, 
    		@RequestParam(name = "student_id") Long studentId ) throws Exception {
		journalService.submitAssignment(text, journalId, studentId);
    }
	
	@RequestMapping(value="/giveFeedback", method=RequestMethod.PUT)
    public void giveFeedback(@RequestParam(name = "feedback_text") String feedbackText, 
    		@RequestParam(name = "journal_id") Long journalId, 
    		@RequestParam(name = "student_id") Long studentId ) throws Exception {
		journalService.giveFeedback(feedbackText, journalId, studentId);
    }
}
