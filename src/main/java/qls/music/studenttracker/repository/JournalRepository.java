package qls.music.studenttracker.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import qls.music.studenttracker.model.Journal;
import qls.music.studenttracker.model.JournalId;

@Repository
public interface JournalRepository extends CrudRepository<Journal, JournalId> {

	public boolean existsByIdJournalId(Long id);

	public List<Journal> findByIdStudentId(Long id);

	public Journal findByIdJournalIdAndIdStudentId(Long journal_id, Long student_id);

}
