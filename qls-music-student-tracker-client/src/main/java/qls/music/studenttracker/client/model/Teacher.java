package qls.music.studenttracker.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Teacher {
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
		public boolean isValidTeacher() {
	  		return this.emailId != null;
	  	}

}
