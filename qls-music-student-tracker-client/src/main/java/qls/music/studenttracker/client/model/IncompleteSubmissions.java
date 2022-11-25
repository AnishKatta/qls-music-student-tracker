package qls.music.studenttracker.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class IncompleteSubmissions extends Journal {
	@JsonIgnore
	@Override
	public String[] getHeaderColumns() {
		final String[] headers = new String[3];
		headers[0] = "Journal Id";
		headers[1] = "Student Id";
		headers[2] = "Submitted Text";
		return headers;
	}
	
	@JsonIgnore
	@Override
	public String[] getColumnValues() {
		
		final String[] row = new String[3];
		row[0] = String.valueOf(this.getId().getJournalId());
		row[1] = String.valueOf(this.getId().getStudentId()); 
		row[2] = this.getText() != null ? this.getText(): "-"; 
		return row;
	}
	
	public static IncompleteSubmissions create(Journal journal) {
		final IncompleteSubmissions ics = new IncompleteSubmissions();
		ics.setId(journal.getId());
		ics.setText(journal.getText());
		return ics;
	}
}
