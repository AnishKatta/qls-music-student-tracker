package qls.music.studenttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import qls.music.studenttracker.model.JournalMaster;
import qls.music.studenttracker.service.JournalService;

@RestController
@RequestMapping("/api")
public class JournalController {
	@Autowired
	JournalService journalService;
	
	@RequestMapping(value="/assignJournal", method=RequestMethod.POST)
    public int assignJournal(@RequestBody JournalMaster journalMaster) {
		return journalService.assignJournalToStudents(journalMaster);
    }
}
