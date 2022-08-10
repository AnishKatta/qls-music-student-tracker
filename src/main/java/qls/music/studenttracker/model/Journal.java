package qls.music.studenttracker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "journal")
public class Journal {
	@EmbeddedId
	private JournalId id;
	
	@Column(name = "text")
	private String text;

	@Column(name = "feedback_text")
	private String feedbackText;

	@Column(name = "submitted_date")
	private Date submittedDate;

	public JournalId getId() {
		return id;
	}

	public void setId(JournalId id) {
		this.id = id;
	}

	public String getFeedbackText() {
		return feedbackText;
	}

	public void setFeedbackText(String feedbackText) {
		this.feedbackText = feedbackText;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}
}
