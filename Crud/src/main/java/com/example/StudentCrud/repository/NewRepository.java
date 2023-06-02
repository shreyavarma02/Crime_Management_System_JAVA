package com.example.StudentCrud.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.example.StudentCrud.domain.User;
import com.example.StudentCrud.domain.Upd;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import .Student;
 
@Repository
public interface NewRepository extends JpaRepository<Upd, Integer> {
    
	Optional<Upd> findByCaseid(int id);

    @Query("SELECT closed FROM Upd u WHERE u.caseid = ?1")
	public int valclose(int caseid);

	@Query("SELECT count(*) FROM Upd u WHERE u.caseid = ?1")
	public int calc(int caseid);

    @Query("SELECT u FROM Upd u WHERE u.closed=1")
	List<Upd> findByCaseid1();

	@Query("SELECT u from Upd u WHERE u.caseid = ?1")
    public List<Upd> findByCaseId(int caseid);
}