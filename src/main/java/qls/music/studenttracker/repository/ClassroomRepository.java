package qls.music.studenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qls.music.studenttracker.model.Classroom;


@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long>  {
	

}
