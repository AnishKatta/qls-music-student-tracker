package qls.music.studenttracker.client.model;

public class ClassEnrollment {
	
	private ClassEnrollmentId id;

	public ClassEnrollment(ClassEnrollmentId classEnrollmentId) {
		this.id = classEnrollmentId;
		// TODO Auto-generated constructor stub
	}
	
	public ClassEnrollment() {
	
		// TODO Auto-generated constructor stub
	}
	public ClassEnrollmentId getId() {
		return id;
	}
	public void setId(ClassEnrollmentId id) {
		this.id = id;
	}
}
