package qls.music.studenttracker.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "class_enrollment")
public class ClassEnrollment {
	@EmbeddedId
	private ClassEnrollmentId id;
	
	public ClassEnrollment(ClassEnrollmentId id) {
		this.id = id;
	}
	
	public ClassEnrollment() {
		
	}

	public ClassEnrollmentId getId() {
		return id;
	}

	public void setId(ClassEnrollmentId id) {
		this.id = id;
	}
}
