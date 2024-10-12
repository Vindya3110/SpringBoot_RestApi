package com.restapidemo.restapi.student;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    public StudentRepository sr;

    public StudentService(StudentRepository sr) {
        this.sr = sr;
    }

    public Student post(Student s){
        return sr.save(s);
    }
    public List<Student> getAll(){
        return sr.findAll();
    }

    public Student getById(int id){
        return sr.findById(id).orElse(null);
    }

    public boolean update(int id,Student s){
        if(this.getById(id)==null){
            return false;
        }
        try{
//            sr.save(s);
            Student ns=this.getById(id);
            ns.setAge(s.getAge());
            ns.setMail(s.getMail());
            ns.setName(s.getName());
            sr.save(ns);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean deleteStud(int id){
        if(this.getById(id)==null){
            return false;
        }
        sr.deleteById(id);
        return true;
    }
}
