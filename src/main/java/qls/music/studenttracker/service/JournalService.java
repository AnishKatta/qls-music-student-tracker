package qls.music.studenttracker.service;

import java.sql.Date;
import java.util.ArrayList;
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
	
	
	public int assignJournalToStudents(JournalMaster journalMaster) throws Exception  {
		boolean journalExists= journalRepository.existsByIdJournalId(journalMaster.getId());
		if(journalExists == true) {
			throw new Exception("journal already assigned!");
		}
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
	
//	public List<Journal> getJournalsForStudent(Long id){
//		return journalRepository.findByIdStudentId(id);
//	}
	
	public int getCompletedJournalsForStudent(Long id){
		List<Journal> journals = journalRepository.findByIdStudentId(id);
		List<Journal> completedJournals = new ArrayList<Journal>();
		int counter = 0;
		for(Journal journal: journals) {
			if (journal.getText() != null) {
				completedJournals.add(journal);
				counter++;
			}
		}
		return counter;
	}
	
	public int getIncompleteJournalsForStudent(Long id){
		List<Journal> journals = journalRepository.findByIdStudentId(id);
		List<Journal> incompleteJournals = new ArrayList<Journal>();
		int counter = 0;
		for(Journal journal: journals) {
			if (journal.getText() == null) {
				incompleteJournals.add(journal);
				counter++;
			}
		}
		return counter;
	}
	
	public void submitAssignment(String text, Long journalId, Long studentId ) throws Exception {
		Journal journal = journalRepository.findByIdJournalIdAndIdStudentId(journalId, studentId);
		if(journal.getText()!=null) {
			throw new Exception("assignment already submitted");
		}
		journal.setText(text);
		journal.setSubmittedDate(new Date(System.currentTimeMillis()));
		journalRepository.save(journal);
		
		
	}
	
	public void giveFeedback(String feedbackText, Long journalId, Long studentId) throws Exception {
		Journal journal = journalRepository.findByIdJournalIdAndIdStudentId(journalId, studentId);
		if(journal.getFeedbackText()!=null) {
			throw new Exception("feedback already given");
		}
		journal.setFeedbackText(feedbackText);
		journalRepository.save(journal);
		
		
	}
	

}
