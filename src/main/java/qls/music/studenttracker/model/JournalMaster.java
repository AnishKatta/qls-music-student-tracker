package qls.music.studenttracker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "journal_master")
public class JournalMaster {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="class_id")
    private Long classId;

    @Column(name="prompt")
    private String prompt;

    @Column(name="assigned_date")
    private Date assignedDate;
    
    @Column(name="due_date")
    //@JsonProperty("due-date")
    private Date dueDate;
    
    @Transient
    @JsonInclude(Include.NON_NULL)
    private Integer dueDaysFromToday;

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


}
