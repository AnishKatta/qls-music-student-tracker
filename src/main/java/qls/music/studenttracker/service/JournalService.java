package qls.music.studenttracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qls.music.studenttracker.model.Journal;
import qls.music.studenttracker.model.JournalId;
import qls.music.studenttracker.model.JournalMaster;
import qls.music.studenttracker.repository.JournalRepository;

@Service
public class JournalService {
	@Autowired
	JournalRepository journalRepository;
	@Autowired
	JournalMasterService journalMasterService;
	@Autowired
	ClassEnrollmentService classEnrollmentService;
	
	public int assignJournalToStudents(JournalMaster journalMaster) {
		JournalMaster journalMasterFromDB = journalMasterService.updateJournal(journalMaster.getId(), journalMaster.getDueDaysFromToday());
		List<Long> studentIdsFromDB = classEnrollmentService.findStudents(journalMasterFromDB.getClassId());
		int numberOfAssignments = 0;
		for(Long studentId: studentIdsFromDB) {
			JournalId journalID = new JournalId(journalMasterFromDB.getId(),studentId);
			Journal journal = new Journal();
			journal.setId(journalID);
			journalRepository.save(journal);
			numberOfAssignments++;
		}
		return numberOfAssignments;
	}

}
