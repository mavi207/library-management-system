package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.Gender;
import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;
import com.example.librarymanagementsystem.model.Student;

import java.util.List;

public interface StudentService {

    public StudentResponse addStudent(StudentRequest studentRequest);

    public Student getStudent(int regNo) ;


    public Student updateAge(int regNo, int age) ;

    public void deleteStudent(int regNo) ;

    public List<Student> getMale() ;

    public List<Student> getAllStudents() ;

    public Student findByEmail(String email) ;

    public Student findByEmailandGender(String email, Gender gender) ;

    public Student blockLibraryCard(int regNo) ;

    public Student activeLibraryCard(int regNo) ;

}
