package com.example.StudentCrud.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "po")
@Getter
@Setter
public class Po {
	@Id
    private int pid;
    private String name;
    private String specialization;
    private int closedcases;
	private int assigned;
    private String securitykey;
    private float ratio;
}