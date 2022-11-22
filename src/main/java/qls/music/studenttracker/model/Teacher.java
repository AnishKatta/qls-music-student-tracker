package qls.music.studenttracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher {
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
	
    public boolean isValidTeacher() {
  		return this.emailId != null;
  	}
}
