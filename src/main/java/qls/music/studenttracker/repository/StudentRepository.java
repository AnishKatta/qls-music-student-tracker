package qls.music.studenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qls.music.studenttracker.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
	public boolean existsByEmailIdAndPassword(String emailId, String password);
}
