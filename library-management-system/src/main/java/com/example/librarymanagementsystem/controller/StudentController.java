package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.Enum.Gender;
import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentServiceImpl studentServiceImpl;

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody StudentRequest studentRequest){
        StudentResponse response = studentServiceImpl.addStudent(studentRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity getStudent(@RequestParam("id") int regNo){
        try{
            StudentResponse studentResponse = studentServiceImpl.getStudent(regNo);
            return new ResponseEntity(studentResponse,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    // update the age of a student  ---> regNo, age
    @PutMapping("/update-age")
    public ResponseEntity updateAge(@RequestParam("id")int regNo,@RequestParam("age")int age){
        StudentResponse studentResponse = studentServiceImpl.getStudent(regNo);
        if(studentResponse==null){
            return new ResponseEntity("Invalid Student id!!",HttpStatus.NOT_ACCEPTABLE);
        }
        studentServiceImpl.updateAge(regNo,age);
        return new ResponseEntity("Age Successfully Updated!!",HttpStatus.ACCEPTED);
    }

    // delete a student --> regNo
    @DeleteMapping("/delete")
    public ResponseEntity deleteStudent(@RequestParam("id") int regNo){
        StudentResponse studentResponse = studentServiceImpl.getStudent(regNo);
        if(studentResponse==null){
            return new ResponseEntity("Invalid Student id!!",HttpStatus.NOT_ACCEPTABLE);
        }
        studentServiceImpl.deleteStudent(regNo);
        return new ResponseEntity("Successfully Deleted!!",HttpStatus.ACCEPTED);
    }


    // get all the students in the db
    @GetMapping("/get-all-student")
    public List<StudentResponse> getAllStudents(){
        List<StudentResponse> studentResponses = studentServiceImpl.getAllStudents();
        return studentResponses;
    }

    // find by email
    @GetMapping("/find-by-email")
    public ResponseEntity findByEmail(@RequestParam("email") String email){
        try{
            StudentResponse studentResponse = studentServiceImpl.findByEmail(email);
            return new ResponseEntity<>(studentResponse, HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    // find by email and gender
    @GetMapping("/find-by-email-gender")
    public ResponseEntity findByEmailAndGender(@RequestParam("email") String email, @RequestParam("gender") Gender gender){
        Student student = studentServiceImpl.findByEmailAndGender(email,gender);

        if(student==null)
            return new ResponseEntity<>("Not Found email or gender incorrect!!",HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(student, HttpStatus.FOUND);
    }



    // block a library card soo he cannot take any book
    @PutMapping("/block-card")
    public ResponseEntity blockLibraryCard(@RequestParam("id")int regNo){
        Student student= studentServiceImpl.blockLibraryCard(regNo);
        if(student==null){
            return new ResponseEntity("Invalid Student id!!",HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity("Card Got Blocked..",HttpStatus.ACCEPTED);
    }

    @PutMapping("/active-card")
    public ResponseEntity activeLibraryCard(@RequestParam("id")int regNo){
        Student student= studentServiceImpl.activeLibraryCard(regNo);
        if(student==null){
            return new ResponseEntity("Invalid Student id!!",HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity("Card Got Active..",HttpStatus.ACCEPTED);
    }


    // get list of all male students
    @GetMapping("/get-male")
    public List<Student> getMale(){
        List<Student> maleList= studentServiceImpl.getMale();
        return maleList;
    }

}
