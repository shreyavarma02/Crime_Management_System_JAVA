package com.example.StudentCrud.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.example.StudentCrud.domain.Closedcase;

 
@Repository
public interface CaseRepository extends JpaRepository<Closedcase, Integer> {
    @Query("SELECT count(*) FROM Closedcase c WHERE c.caseid = ?1")
	public int getCount(int caseid);

    
	
}