package com.example.StudentCrud.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.example.StudentCrud.domain.User;

import jakarta.transaction.Transactional;

import com.example.StudentCrud.domain.Upd;
import com.example.StudentCrud.domain.Pi;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import .Student;
 
@Repository
public interface PiRepository extends JpaRepository<Pi, Integer> {
    
	Optional<Pi> findByPid(int pid);

    @Transactional
    void deleteByPid(int pid);

    @Query("SELECT count(*) FROM Pi p WHERE p.pid=?1 and p.securitykey=?2")
	public int pidmatchsec1(int pid,String securitykey);
}