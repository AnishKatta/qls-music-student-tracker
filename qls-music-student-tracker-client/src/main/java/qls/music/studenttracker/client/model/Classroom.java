package qls.music.studenttracker.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Classroom extends DataTable {

	private Long id;
	private Long teacherId;
	private String name;

	public Classroom(Long teacherId, String name) {
		this.teacherId = teacherId;
		this.name = name;
	}
	
	public Classroom() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonIgnore
	public String[] getHeaderColumns() {
		final String[] headers = new String[2];
		headers[0] = "Class Id";
		headers[1] = "Name";
		return headers;
	}
	
	@JsonIgnore
	public String[] getColumnValues() {
		final String[] row = new String[2];
		row[0] = String.valueOf(this.id);
		row[1] = this.name; 
		return row;
	}
	
}
