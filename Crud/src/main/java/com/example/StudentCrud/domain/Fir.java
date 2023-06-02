package com.example.StudentCrud.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fir")
@Getter
@Setter
public class Fir {
	@Id
    private int caseid;
    private String type;
    private String description;
    private int pid;
	private String location;
	private String username;
    
	/*public Fir() {

	}
	public Fir(int caseid, String type,String description, int pid, String location) {
	
		this.caseid = caseid;
		this.description = description;
		this.pid = pid;
		this.location=location;
		
	}
	public int getCaseid() {
		return caseid;
	}
	public void setCaseid(int caseid) {
		this.caseid = caseid;
	}
    public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getpid() {
		return pid;
	}
	public void setpid(int pid) {
		this.pid = pid;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}*/
	

}