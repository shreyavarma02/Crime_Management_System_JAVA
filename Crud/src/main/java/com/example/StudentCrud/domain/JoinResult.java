package com.example.StudentCrud.domain;



public class JoinResult {
    public JoinResult() {

	}
	public int caseid;
    public String updates;
    
    public JoinResult(int caseid, String updates) {
        this.caseid = caseid;
        this.updates = updates;
    }

    public int getCaseId() {
        return caseid;
    }

    public String getUpdates() {
        return updates;
    }

}
