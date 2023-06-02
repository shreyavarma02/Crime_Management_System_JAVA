package com.example.StudentCrud.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "upd")
public class Upd {
    @Id
    private int caseid;
    private String updates;
    private String addupd;
    private int closed;

    public Upd() {

    }

    public Upd(int caseid, String updates, String addupd, int closed) {

        this.caseid = caseid;
        this.updates = updates;
        this.addupd = addupd;
        this.closed = closed;
    }

    public int getCaseid() {
        return caseid;
    }

    public void setCaseid(int caseid) {
        this.caseid = caseid;
    }

    public String getUpdates() {
        return updates;
    }

    public void setUpdates(String updates) {
        this.updates = updates;
    }

    public String getAddupd() {

        return addupd;
    }

    public void setAddupd(String addupd) {
        this.addupd = addupd;
    }

    public int getClosed() {
        return closed;
    }

    public void setClosed(int closed) {
        this.closed = closed;
    }
}
