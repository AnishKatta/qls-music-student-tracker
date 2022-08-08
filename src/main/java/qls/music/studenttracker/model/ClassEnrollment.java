package qls.music.studenttracker.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "class_enrollment")
public class ClassEnrollment {
    @EmbeddedId
    private ClassEnrollmentId id;

	public ClassEnrollmentId getId() {
		return id;
	}

	public void setId(ClassEnrollmentId id) {
		this.id = id;
	}


    
    /**
    @Column(name="student_id")
    private Long studentId;

    @Column(name="class_id")
    private Long classId;

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}
**/
    
}
