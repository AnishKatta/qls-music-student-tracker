package qls.music.studenttracker.client.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UngradedSubmissions extends Journal {
	@JsonIgnore
	@Override
	public String[] getHeaderColumns() {
		final String[] headers = new String[5];
		int columnIndex = -1;
		headers[++columnIndex] = "Journal Id";
		headers[++columnIndex] = "Student Id";
		headers[++columnIndex] = "Prompt";
		headers[++columnIndex] = "Submitted Text";
		headers[++columnIndex] = "Submission Date";
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
		
		final String[] row = new String[5];
		int columnIndex = -1;
		row[++columnIndex] = String.valueOf(this.getId().getJournalId());
		row[++columnIndex] = String.valueOf(this.getId().getStudentId());		
		row[++columnIndex] = this.getPrompt(); 
		row[++columnIndex] = this.getText();
		row[++columnIndex] = strDate;

		
		return row;
	}
	
	public static UngradedSubmissions create(Journal journal, String prompt) {
		UngradedSubmissions us = new UngradedSubmissions();
		us.setId(journal.getId());
		us.setPrompt(prompt);
		us.setText(journal.getText());
		us.setSubmittedDate(journal.getSubmittedDate());
		return us;
	}
}
