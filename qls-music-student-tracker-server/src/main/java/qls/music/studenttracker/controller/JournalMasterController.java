package qls.music.studenttracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qls.music.studenttracker.model.JournalMaster;
import qls.music.studenttracker.service.JournalMasterService;

@RestController
@RequestMapping("/api")
public class JournalMasterController {
	@Autowired
	JournalMasterService journalMasterService;
	
	@RequestMapping(value="/createJournal", method=RequestMethod.POST)
    public JournalMaster createJournal(@RequestBody JournalMaster journalMaster) {
        return journalMasterService.createJournal(journalMaster);
    }
	
	@RequestMapping(value="/updateJournal", method=RequestMethod.PUT)
    public JournalMaster updateJournal(@RequestBody JournalMaster journalMaster) {
        return journalMasterService.updateJournal(journalMaster.getId(), journalMaster.getDueDaysFromToday());
    }
	
	@RequestMapping(value="/getAllJournalMaster", method=RequestMethod.GET)
    public List<JournalMaster> getAllJournalMaster() {
        return journalMasterService.getAllJournalMaster();
    }
	

	@RequestMapping(value="/journalMasterExists", method=RequestMethod.GET)
    public boolean journalMasterExists(@RequestParam(name = "id") Long id) {
        return journalMasterService.journalMasterExists(id);
    }
	
	
	
}
