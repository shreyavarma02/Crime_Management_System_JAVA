package com.example.StudentCrud.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.cglib.core.Local;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name = "user")

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String role;
	private int pid;
	private String securitykey;
	public User()
	{
	}
	public User(int id, String username,String password, String role,int pid) {
	
		this.id = id;
		this.username=username;
		this.password=password;
		this.role=role;
		this.pid=pid;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role =role;
	}
	public int getPid() {
		System.out.println("GET");
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getSecuritykey() {
		return securitykey;
	}

	public void setSecuritykey(String securitykey) {
		this.securitykey = securitykey;
	}


}