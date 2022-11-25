package qls.music.studenttracker.client.model;

import java.util.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Journal extends DataTable {
	
	private JournalId id;

	@JsonInclude(Include.NON_NULL)
	private String text;

	@JsonInclude(Include.NON_NULL)
	private String feedbackText;

	@JsonInclude(Include.NON_NULL)
	private Date submittedDate;
	
	@JsonInclude(Include.NON_NULL)
	private Date dueDate;

	@JsonInclude(Include.NON_NULL)
	private Integer earnedPoints;
	
	@JsonInclude(Include.NON_NULL)
	private Integer maxPoints;
	

	@JsonInclude(Include.NON_NULL)
	private String prompt;
	


	public Journal(JournalId id, String text) {
		this.id = id;
		this.text = text;
	}
	
	public Journal() {
		// TODO Auto-generated constructor stub
	}

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
	
	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getEarnedPoints() {
		return earnedPoints;
	}

	public void setEarnedPoints(Integer earnedPoints) {
		this.earnedPoints = earnedPoints;
	}
	
	public Integer getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(Integer maxPoints) {
		this.maxPoints = maxPoints;
	}
	
	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	
	@JsonIgnore
	@Override
	public String[] getHeaderColumns() {
		final String[] headers = new String[7];
		headers[0] = "Journal Id";
		headers[1] = "Prompt";
		headers[2] = "Submitted Text";
		headers[3] = "Submission Date";
		headers[4] = "Feedback";
		headers[5] = "Max Points";
		headers[6] = "Points earned";
		return headers;
	}
	
	@JsonIgnore
	@Override
	public String[] getColumnValues() {
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		final String strDate;
		if(this.submittedDate!=null) {
			strDate = dateFormat.format(this.submittedDate);
		}else {
			strDate = "";
		}
		
		final String[] row = new String[7];
		row[0] = String.valueOf(this.id.getJournalId());
		row[1] = this.prompt; 
		row[2] = this.text; 
		row[3] = strDate;
		row[4] = this.feedbackText != null ? this.feedbackText : "-"; 
		row[5] = this.maxPoints != null ? String.valueOf(this.maxPoints) : "-";
		row[6] = this.earnedPoints != null ? String.valueOf(this.earnedPoints) : "-";
		return row;
	}
}