package com.example.StudentCrud.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.example.StudentCrud.domain.User;
import com.example.StudentCrud.domain.Upd;
import com.example.StudentCrud.domain.Fir;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import .Student;
 
@Repository
public interface FirRepository extends JpaRepository<Fir, Integer> {
    @Query("select pid from Fir f where caseid=?1")
    public int getPid(int caseid);

    @Query("select type from Fir f where caseid=?1")
    public String gettype(int caseid);

    
    Optional<Fir> findByCaseid(int id);

    @Query("select f from Fir f where pid=?1")
    List<Fir> getopencases(int pid);

    @Query("SELECT f FROM Fir f WHERE f.username = ?1")
    public List<Fir> findByUsername(String username);

    @Query("SELECT f.location,count(*) as c FROM Fir f group by f.location")
    public List<String> getinf();
	
}