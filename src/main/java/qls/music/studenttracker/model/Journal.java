package qls.music.studenttracker.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "journal")
public class Journal {
	@EmbeddedId
	private JournalId id;

	@Column(name = "text")
	@JsonInclude(Include.NON_NULL)
	private String text;

	@Column(name = "feedback_text")
	@JsonInclude(Include.NON_NULL)
	private String feedbackText;

	@Column(name = "submitted_date")
	@JsonInclude(Include.NON_NULL)
	private Date submittedDate;

	@Column(name = "earned_points")
	@JsonInclude(Include.NON_NULL)
	private Integer earnedPoints;

	public JournalId getId() {
		return id;
	}

	public void setId(JournalId id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public Integer getEarnedPoints() {
		return earnedPoints;
	}

	public void setEarnedPoints(Integer earnedPoints) {
		this.earnedPoints = earnedPoints;
	}
}
