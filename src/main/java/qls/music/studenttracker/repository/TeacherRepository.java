package qls.music.studenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qls.music.studenttracker.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	public boolean existsByEmailIdAndPassword(String emailId, String password);
	public Teacher findByEmailIdAndPassword(String emailId, String password);

}
