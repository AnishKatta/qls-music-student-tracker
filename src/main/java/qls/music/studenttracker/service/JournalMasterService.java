package qls.music.studenttracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qls.music.studenttracker.model.JournalMaster;
import qls.music.studenttracker.repository.JournalMasterRepository;

@Service
public class JournalMasterService {

	@Autowired
    JournalMasterRepository journalMasterRepository;
	
	public JournalMaster createJournal(JournalMaster journalMaster) {
		return journalMasterRepository.save(journalMaster);
	}
}
