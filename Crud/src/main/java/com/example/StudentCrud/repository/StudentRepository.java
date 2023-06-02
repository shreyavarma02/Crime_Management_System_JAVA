package com.example.StudentCrud.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.example.StudentCrud.domain.User;
import com.example.StudentCrud.domain.Upd;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import .Student;
 
@Repository
public interface StudentRepository extends JpaRepository<User, Integer> {
    @Query("SELECT count(*) FROM User u WHERE u.username = ?1")
	public int existsByUsername(String username);
    //Boolean existsByEmail(String email);
    @Query("SELECT count(*) FROM User u WHERE u.username = ?1 and u.password=?2")
	public int checkmatch(String username,String password);
    @Query("SELECT role FROM User u WHERE u.username = ?1")
	public String getrole(String username);
    @Query("SELECT pid FROM User u WHERE u.username = ?1")
	public int getpidusr(String username);
    //@Query("SELECT u FROM User u WHERE u.username = ?1")
	//Optional<Upd> findByCaseid(int id);
}