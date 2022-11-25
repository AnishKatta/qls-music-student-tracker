package qls.music.studenttracker.client.model;

import java.io.Serializable;

public class JournalId implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long journalId;

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
