package qls.music.studenttracker.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
	
	public JournalMaster updateJournal(Long id, int dueDaysFromToday) {
		Optional<JournalMaster> journalMasterOptional = journalMasterRepository.findById(id);
		if(journalMasterOptional.isPresent()) {
			JournalMaster journalMaster = journalMasterOptional.get();
			journalMaster.setAssignedDate(new Date(System.currentTimeMillis()));
			Date dueDate = Date.valueOf(LocalDate.now().plusDays(dueDaysFromToday + 1));
			journalMaster.setDueDate(dueDate);
			journalMasterRepository.save(journalMaster);
			return journalMasterRepository.findById(id).get();
		}else {
			return null;
		}
		
	}
	
	public List<JournalMaster> getAllJournalMaster(){
		return (List<JournalMaster>) journalMasterRepository.findAll();
	}
	
	public boolean journalMasterExists(Long id) {
		return journalMasterRepository.existsById(id);
	}
	
	
	
	
}
