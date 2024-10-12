package com.restapidemo.restapi.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    public StudentService ss;

    @Autowired
    public StudentController(StudentService ss) {
        this.ss = ss;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAll(){
        List<Student> l=ss.getAll();
        try{
            System.out.println("getall successfull");
            return new ResponseEntity<>(l, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("Error:"+e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<Student> getByID(@PathVariable("id") int id){
        Student ns=ss.getById(id);
        try{
            System.out.println("getall successfull");
            return new ResponseEntity<>(ns, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("Error:"+e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/creat")
    public ResponseEntity<Student> post(@RequestBody Student s){
        try {
            Student ns = ss.post(s);
            return new ResponseEntity<>(ns,HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println("Error:"+e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id")int id,@RequestBody Student s){
        try{
            if(ss.update(id, s)){
                return new ResponseEntity<>(true,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int id){
        try{
            if(ss.deleteStud(id)){
                return new ResponseEntity<>(true,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
        }
    }


}
