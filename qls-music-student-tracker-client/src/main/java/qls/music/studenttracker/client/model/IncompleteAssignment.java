package qls.music.studenttracker.client.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class IncompleteAssignment extends Journal {
	
	@JsonIgnore
	@Override
	public String[] getHeaderColumns() {
		final String[] headers = new String[4];
		headers[0] = "Journal Id";
		headers[1] = "Prompt";
		headers[2] = "Due Date";
		headers[3] = "Max Points";
		return headers;
	}
	
	@JsonIgnore
	@Override
	public String[] getColumnValues() {
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		final String strDate;
		if(this.getDueDate()!=null) {
			strDate = dateFormat.format(this.getDueDate());
		}else {
			strDate = "";
		}
		
		final String[] row = new String[4];
		row[0] = String.valueOf(this.getId().getJournalId());
		row[1] = this.getPrompt(); 
		row[2] = strDate; 
		row[3] = this.getMaxPoints() != null ? String.valueOf(this.getMaxPoints()) : "-";
		return row;
	}
	
	public static IncompleteAssignment create(Journal journal) {
		final IncompleteAssignment ica = new IncompleteAssignment();
		ica.setId(journal.getId());
		ica.setMaxPoints(journal.getMaxPoints());
		ica.setPrompt(journal.getPrompt());
		ica.setDueDate(journal.getDueDate());
		return ica;
	}

}
