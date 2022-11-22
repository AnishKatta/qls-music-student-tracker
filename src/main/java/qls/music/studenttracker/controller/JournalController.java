package qls.music.studenttracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qls.music.studenttracker.model.Journal;
import qls.music.studenttracker.model.JournalMaster;
import qls.music.studenttracker.service.JournalService;

@RestController
@RequestMapping("/api")
public class JournalController {
	@Autowired
	JournalService journalService;

	@RequestMapping(value = "/assignJournal", method = RequestMethod.POST)
	public int assignJournal(@RequestBody JournalMaster journalMaster) throws Exception {
		return journalService.assignJournalToStudents(journalMaster);
	}

	@RequestMapping(value="/getJournalsForStudent", method=RequestMethod.GET)
    public List<Journal> getJournalsForStudent(@RequestParam(name = "student_id") Long student_id) {
		return journalService.getJournalsForStudent(student_id);
    }

	@RequestMapping(value = "/getCompletedJournalsForStudent", method = RequestMethod.GET)
	public List<Journal> getCompletedJournalsForStudent(@RequestParam(name = "student_id") Long studentId) {
		return journalService.getCompletedJournalsForStudent(studentId);
	}

	@RequestMapping(value = "/getIncompleteJournalsForStudent", method = RequestMethod.GET)
	public List<Journal> getIncompleteJournalsForStudent(@RequestParam(name = "student_id") Long student_id) {
		return journalService.getIncompleteJournalsForStudent(student_id);
	}
	
	@RequestMapping(value = "/findJournal", method = RequestMethod.GET)
	public Journal isValidJournal(@RequestParam(name = "journal_id") Long journalId, 
			@RequestParam(name = "student_id") Long studentId) {
		return journalService.findJournal(journalId, studentId);
	}

	@RequestMapping(value = "/submitAssignment", method = RequestMethod.PUT)
	public Journal submitAssignment(@RequestBody Journal journal) throws Exception {
		return journalService.submitAssignment(journal.getText(), journal.getId().getJournalId(),
				journal.getId().getStudentId());
	}

	@RequestMapping(value = "/giveFeedback", method = RequestMethod.PUT)
	public Journal giveFeedback(@RequestBody Journal journal) throws Exception {
		return journalService.giveFeedback(journal.getFeedbackText(), journal.getId().getJournalId(),
				journal.getId().getStudentId(), journal.getEarnedPoints());
	}
}
