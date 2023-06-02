package com.example.StudentCrud.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.example.StudentCrud.domain.User;

import jakarta.transaction.Transactional;

import com.example.StudentCrud.domain.Upd;
import com.example.StudentCrud.domain.Po;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import .Student;
 
@Repository
public interface PoRepository extends JpaRepository<Po, Integer> {
    
	Optional<Po> findByPid(int pid);

    @Query("SELECT p FROM Po p WHERE p.specialization=?1")
	List<Po> findPo(String specialization);

    @Query("SELECT count(*) FROM Po p WHERE p.pid=?1 and p.securitykey=?2")
	public int pidmatchsec(int pid,String securitykey);

	@Transactional
    void deleteByPid(int pid);

	@Transactional
	@Modifying
	@Query("update Po p set p.ratio=p.assigned/p.closedcases where p.closedcases!=0")
	public void poupdate();

	@Query("select p from Po p order by p.ratio desc limit 3")
	public List<Po> posort();
}