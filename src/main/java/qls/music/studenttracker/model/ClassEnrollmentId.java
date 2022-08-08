package qls.music.studenttracker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ClassEnrollmentId implements Serializable {
	
	@Column(name="student_id")
	private Long studentId;
	
	@Column(name="class_id")
	private Long classId;

	public ClassEnrollmentId(Long studentId, Long classId) {
		super();
		this.studentId = studentId;
		this.classId = classId;
	}
	
	public ClassEnrollmentId() {
		
	}

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
}
