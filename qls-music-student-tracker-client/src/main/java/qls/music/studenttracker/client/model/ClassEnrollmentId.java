package qls.music.studenttracker.client.model;

import java.io.Serializable;

public class ClassEnrollmentId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long studentId;
	
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
