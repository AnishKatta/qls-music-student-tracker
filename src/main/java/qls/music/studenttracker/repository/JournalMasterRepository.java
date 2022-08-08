package qls.music.studenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qls.music.studenttracker.model.JournalMaster;

@Repository
public interface JournalMasterRepository extends JpaRepository<JournalMaster,Long> {
	
}
