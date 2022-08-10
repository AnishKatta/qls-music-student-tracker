package qls.music.studenttracker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class JournalId implements Serializable {
	@Column(name = "journal_id")
	private Long journalId;

	@Column(name = "student_id")
	private Long studentId;

	public JournalId(Long journalId, Long studentId) {
		super();
		this.journalId = journalId;
		this.studentId = studentId;
	}

	public JournalId() {

	}

	public Long getJournalId() {
		return journalId;
	}

	public void setJournalId(Long journalId) {
		this.journalId = journalId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

}
