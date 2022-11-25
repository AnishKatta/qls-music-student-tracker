package qls.music.studenttracker.client.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CompletedAssignment extends Journal {
	
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
		if(this.getSubmittedDate()!=null) {
			strDate = dateFormat.format(this.getSubmittedDate());
		}else {
			strDate = "";
		}
		
		final String[] row = new String[7];
		row[0] = String.valueOf(this.getId().getJournalId());
		row[1] = this.getPrompt(); 
		row[2] = this.getText(); 
		row[3] = strDate;
		row[4] = this.getFeedbackText() != null ? this.getFeedbackText() : "-"; 
		row[5] = this.getMaxPoints() != null ? String.valueOf(this.getMaxPoints()) : "-";
		row[6] = this.getEarnedPoints() != null ? String.valueOf(this.getEarnedPoints()) : "-";
		return row;
	}
	
	public static CompletedAssignment create(Journal journal) {
		final CompletedAssignment ca = new CompletedAssignment();
		ca.setEarnedPoints(journal.getEarnedPoints());
		ca.setFeedbackText(journal.getFeedbackText());
		ca.setId(journal.getId());
		ca.setMaxPoints(journal.getMaxPoints());
		ca.setPrompt(journal.getPrompt());
		ca.setText(journal.getText());
		ca.setSubmittedDate(journal.getSubmittedDate());
		return ca;
	}
}
