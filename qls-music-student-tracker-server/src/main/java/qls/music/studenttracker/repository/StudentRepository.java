package qls.music.studenttracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qls.music.studenttracker.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
	
	public boolean existsByEmailIdAndPassword(String emailId, String password);
	
	public Student findByEmailIdAndPassword(String emailId, String password);
	
	public List<Student> findAll();
	
	public Optional<Student> findById(Long id);
	
	public List<Student> findAllById(Iterable<Long> studentIds);
	
	public boolean existsById(Long id);
	
}
