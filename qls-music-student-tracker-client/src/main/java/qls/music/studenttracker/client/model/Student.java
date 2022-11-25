package qls.music.studenttracker.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Student extends DataTable {
	private Long id;
	private String name;
	private String emailId;
	private String password;
	
	 public Long getId() {
	  		return id;
	  	}

	  	public void setId(Long id) {
	  		this.id = id;
	  	}

	  	public String getName() {
	  		return name;
	  	}

	  	public void setName(String name) {
	  		this.name = name;
	  	}

	  	public String getEmailId() {
	  		return emailId;
	  	}

	  	public void setEmailId(String emailId) {
	  		this.emailId = emailId;
	  	}

	  	public String getPassword() {
	  		return password;
	  	}

	  	public void setPassword(String password) {
	  		this.password = password;
	  	}
	  	
	  	@JsonIgnore
		public boolean isValidStudent() {
	  		return this.emailId != null;
	  	}
	  	
		@JsonIgnore
		public String[] getHeaderColumns() {
			final String[] headers = new String[2];
			headers[0] = "Student Id";
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
