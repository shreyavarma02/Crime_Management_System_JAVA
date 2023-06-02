package com.example.StudentCrud.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pi")
@Getter
@Setter

public class Pi
{
    @Id
    private int pid;
    private String name;
    private String branch;
    private String assigned;
    private String securitykey;
}



    

