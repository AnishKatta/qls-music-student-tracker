package qls.music.studenttracker.client.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class JournalMaster extends DataTable {

	private Long id;

	private Long classId;

	private String prompt;

	private Date assignedDate;

	// @JsonProperty("due-date")
	private Date dueDate;

	private int maxPoints;

	@JsonInclude(Include.NON_NULL)
	private Integer dueDaysFromToday;
	
	public JournalMaster(Long classId, String prompt, int maxPoints) throws ParseException {
		this.classId = classId;
		this.prompt = prompt;
		this.maxPoints = maxPoints;
	} 
	
	
	public JournalMaster() {
		
	}


	public Integer getDueDaysFromToday() {
		return dueDaysFromToday;
	}

	public void setDueDaysFromToday(Integer dueDaysFromToday) {
		this.dueDaysFromToday = dueDaysFromToday;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public Date getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public int getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}
	
	@JsonIgnore
	@Override
	public String[] getHeaderColumns() {
		final String[] headers = new String[5];
		headers[0] = "Journal Id";
		headers[1] = "Prompt";
		headers[2] = "Assigned Date";
		headers[3] = "Due Date";
		headers[4] = "Max Points";
		return headers;
	}
	
	@JsonIgnore
	@Override
	public String[] getColumnValues() {
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		final String strAssignedDate;
		final String strDueDate;
		if(this.assignedDate!=null) {
			strAssignedDate = dateFormat.format(this.assignedDate);
		}else {
			strAssignedDate = "";
		}
		if(this.dueDate!=null) {
			strDueDate = dateFormat.format(this.dueDate);
		}else {
			strDueDate = "";
		}
		
		
		final String[] row = new String[5];
		row[0] = String.valueOf(this.id);
		row[1] = this.prompt; 
		row[2] = strAssignedDate; 
		row[3] = strDueDate;
		row[4] = (Integer)this.maxPoints != null ? String.valueOf(this.maxPoints) : "-";
		return row;
	}
}
