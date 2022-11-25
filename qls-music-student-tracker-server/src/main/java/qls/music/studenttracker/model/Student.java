package qls.music.studenttracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "student")
public class Student {
	 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
	@Column(name="name")
    private String name;

    @Column(name="email_id")
    private String emailId;

    @Column(name="password")
    private String password;
    
    public void setId(Long id) {
  		this.id = id;
  	}
   
    public Long getId() {
  		return id;
  	}

    public void setName(String name) {
  		this.name = name;
  	}
  	
  	public String getName() {
  		return name;
  	}

  	public void setEmailId(String emailId) {
  		this.emailId = emailId;
  	}

  	public String getEmailId() {
  		return emailId;
  	}

  	public void setPassword(String password) {
  		this.password = password;
  	}

  	public String getPassword() {
  		return password;
  	}
  	
  	public boolean isValidStudent() {
  		return this.emailId != null;
  	}

  	


}
