package com.example.StudentCrud.service;
import java.util.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.StudentCrud.domain.Fir;
import com.example.StudentCrud.repository.FirRepository;
import com.example.StudentCrud.domain.Upd;
import com.example.StudentCrud.repository.NewRepository;

import jakarta.persistence.criteria.Join;

import com.example.StudentCrud.domain.JoinResult;

@Service
public class joinService {
	
	@Autowired
    private FirRepository firRepo;
	
	@Autowired
    private NewRepository updRepo;

	public List<JoinResult> getJoinResultForUsername(String username) {
		List<Fir> firList = firRepo.findByUsername(username);
        System.out.println("firlist "+firList);
        // System.out.println("fir =");
		// System.out.println(firList.getUsername());
        // System.out.println(firList.getCaseid());
		List<JoinResult> joinResults = new ArrayList<>();
		for (Fir f: firList) {
            System.out.println("caseid "+f.getCaseid());
            List<Upd> updList = updRepo.findByCaseId((int) f.getCaseid());
            System.out.println(updList);
        
        for (Upd upd : updList) {
            joinResults.add(new JoinResult(upd.getCaseid(), upd.getUpdates()));
            System.out.println(upd);
        }
        for (JoinResult j : joinResults) {
            
            System.out.println(j.getCaseId());
            System.out.println(j.getUpdates());

        }
		
            
        }
		
        
		System.out.println(joinResults);
		return joinResults;
	}
	
	
}

