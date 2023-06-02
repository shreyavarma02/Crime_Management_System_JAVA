package com.example.StudentCrud.service;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentCrud.domain.Upd;
import com.example.StudentCrud.domain.User;
import com.example.StudentCrud.domain.Closedcase;
import com.example.StudentCrud.domain.Fir;
import com.example.StudentCrud.domain.Pi;
import com.example.StudentCrud.domain.Po;
import com.example.StudentCrud.repository.StudentRepository;
import com.example.StudentCrud.repository.NewRepository;
import com.example.StudentCrud.repository.CaseRepository;
import com.example.StudentCrud.repository.FirRepository;
import com.example.StudentCrud.repository.PoRepository;

import com.example.StudentCrud.repository.PiRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repo;
    @Autowired
    private NewRepository repo1;
    @Autowired
    private CaseRepository repo2;
    @Autowired
    private FirRepository repo3;
    @Autowired
    private PoRepository repo4;
    @Autowired
    private PiRepository repo5;
public List<User> listAll() {
        return repo.findAll();
    }
    public List<Pi> listAll1() {
        return repo5.findAll();
    }
    public List<Po> listAll2() {
        return repo4.findAll();
    }
    public List<String> getinf()
    {
        return repo3.getinf();
    }
    public void save(User std) {
        
        repo.save(std);
    }
    public void savepo(Po p) {
        repo4.save(p);
    }
    public void saveupd(Upd u) {
        repo1.save(u);
    }
    public void savefir(Fir fp) {
        repo3.save(fp);
    }
    public void saveclosed(Closedcase first) {
        repo2.save(first);
    }
    public void savepi(Pi p) {
        repo5.save(p);
    }
    public Pi getpi(int id) {
        return repo5.findByPid(id).get();
    }

    public Po getpo(int id) {
        return repo4.findByPid(id).get();
    }
    public void deletepi(int pid) {
        repo5.deleteByPid(pid);
    }
    public void deletepo(int pid) {
        repo4.deleteByPid(pid);
    }
    public int existsornot(String username)
    {
        return repo.existsByUsername(username);

    }
    public int getCount(int caseid)
    {
        return repo2.getCount(caseid);

    }
    public int getpid(int caseid)
    {
        return repo3.getPid(caseid);

    }
    public int getpidusr(String usrname)
    {
        return repo.getpidusr(usrname);

    }
    public String gettype(int caseid)
    {
        return repo3.gettype(caseid);

    }
    public int valclose(int caseid)
    {
        return repo1.valclose(caseid);

    }
    public int pidmatchsec(int pid,String securitykey)
    {
        System.out.println(securitykey);
        return repo4.pidmatchsec(pid,securitykey);

    }
    public int pidmatchsec1(int pid,String securitykey)
    {
        System.out.println(securitykey);
        return repo5.pidmatchsec1(pid,securitykey);

    }
    public int matches(String username,String password)
    {
        return repo.checkmatch(username,password);
    }
    public String getrole(String username)
    {
        return repo.getrole(username);
    }

    public Upd find(int id) {
        return repo1.findByCaseid(id).get();
    }
    public int calc(int id) {
        return repo1.calc(id);
    }
    public Po findByPid(int id) {
        return repo4.findByPid(id).get();
    }
    public Fir findCaseid(int id) {
        return repo3.findByCaseid(id).get();
    }
    public List<Upd> getTotal() {
        return repo1.findByCaseid1();
    }
    public List<Po> findPo(String specialization) {
        return repo4.findPo(specialization);
    }
    public List<Fir> getopencases(int pid) {
        return repo3.getopencases(pid);
    }
    public void delete(int id) {
        repo.deleteById(id);
    }
    
    public void poupdate()
    {
        repo4.poupdate();
    }
    public List<Po> posort()
    {
        return repo4.posort();
    }
    
    
}