package qls.music.studenttracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import qls.music.studenttracker.model.Journal;
import qls.music.studenttracker.model.JournalId;

@Repository
public interface JournalRepository extends CrudRepository<Journal, JournalId> {

	public boolean existsByIdJournalId(Long id);
	
	public boolean existsByIdJournalIdAndIdStudentId(Long journal_id, Long student_id);

	public List<Journal> findByIdStudentId(Long id);

	public Journal findByIdJournalIdAndIdStudentId(Long journal_id, Long student_id);

	@Query("SELECT j FROM Journal j WHERE j.text IS NOT NULL" 
			  )
	public List<Journal> findAllCompletedJournals();
	
	
	@Query("SELECT j FROM Journal j WHERE j.id.journalId = :pJournalId and j.text IS NULL" 
			  )
	public List<Journal> findIncompleteJournals(@Param("pJournalId")Long journalId);
	
	
	@Query("SELECT j FROM Journal j WHERE j.id.journalId = :pJournalId and j.text IS NOT NULL and j.feedbackText IS NULL and j.earnedPoints is NULL" 
			  )
	public List<Journal> findUngradedAssignments(@Param("pJournalId")Long journalId);
	
	
	
	@Query("SELECT j FROM Journal j WHERE j.id.studentId = :pStudentId" 
			  )
	public List<Journal> findJournalsForStudent(@Param("pStudentId")Long studentId);
	
}
