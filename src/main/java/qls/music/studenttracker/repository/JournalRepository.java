package qls.music.studenttracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import qls.music.studenttracker.model.Journal;
import qls.music.studenttracker.model.JournalId;

@Repository
public interface JournalRepository extends CrudRepository<Journal, JournalId>{

}
