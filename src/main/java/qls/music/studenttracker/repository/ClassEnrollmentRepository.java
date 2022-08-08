package qls.music.studenttracker.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import qls.music.studenttracker.model.ClassEnrollment;
import qls.music.studenttracker.model.ClassEnrollmentId;

@Repository
public interface ClassEnrollmentRepository extends CrudRepository<ClassEnrollment, ClassEnrollmentId> {
	public List<ClassEnrollment> findByIdClassId(Long classId);
}
