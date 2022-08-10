package qls.music.studenttracker.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import qls.music.studenttracker.model.JournalMaster;

@Repository
public interface JournalMasterRepository extends CrudRepository<JournalMaster,Long> {
	public Optional<JournalMaster> findById(Long id);
}
