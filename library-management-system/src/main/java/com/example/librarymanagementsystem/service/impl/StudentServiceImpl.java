package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.Enum.CardStatus;
import com.example.librarymanagementsystem.Enum.Gender;
import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;
import com.example.librarymanagementsystem.exception.StudentNotFoundException;
import com.example.librarymanagementsystem.model.LibraryCard;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.repository.StudentRepository;
import com.example.librarymanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.librarymanagementsystem.transformer.StudentTransformer.StudentRequestToStudent;
import static com.example.librarymanagementsystem.transformer.StudentTransformer.StudentToStudentResponse;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    public StudentResponse addStudent(StudentRequest studentRequest) {

        Student student = StudentRequestToStudent(studentRequest);

        LibraryCard libraryCard=new LibraryCard();
        libraryCard.setCardNo(String.valueOf(UUID.randomUUID()));
        libraryCard.setCardstatus(CardStatus.Active);
        libraryCard.setStudent(student);
        student.setLibraryCard(libraryCard);// set library card for student
        Student savedStudent = studentRepository.save(student);// save both student and library card

        return StudentToStudentResponse(savedStudent);
    }

    public StudentResponse getStudent(int regNo) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if (studentOptional.isEmpty()) {
            throw new StudentNotFoundException("Invalid Student Id!!");
        }
        Student student = studentOptional.get();
        return StudentToStudentResponse(student);
    }


    public Student updateAge(int regNo, int age) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setAge(age);
            studentRepository.save(student); // Save the updated student
            return student; // Return the updated student
        }
        return null; // Return null if the student is not found
    }

    public void deleteStudent(int regNo) {
        studentRepository.deleteById(regNo);
    }

    public List<Student> getMale() {
        List<Student> maleList=studentRepository.findByGender(Gender.Male);
        return maleList;
    }

    public List<StudentResponse> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponse> studentResponses = new ArrayList<>();
        for(var ele : students){
            studentResponses.add(StudentToStudentResponse(ele));
        }
        return studentResponses;
    }

    public StudentResponse findByEmail(String email) {
        Student student = studentRepository.findByEmail(email);
        if(student==null){
            throw new StudentNotFoundException("Email id is wrong!!");
        }
        return StudentToStudentResponse(student);
    }

    public Student findByEmailAndGender(String email, Gender gender) {
        Student student = studentRepository.findByEmailAndGender(email,gender);
        return student;
    }

    public Student blockLibraryCard(int regNo) {

        Optional<Student> student = studentRepository.findById(regNo);
        if(student==null){
            return null;
        }

        Student responseStudent = student.get();
        responseStudent.getLibraryCard().setCardstatus(CardStatus.Blocked);
        studentRepository.save(responseStudent);
        return responseStudent;
    }

    public Student activeLibraryCard(int regNo) {

        Optional<Student> student = studentRepository.findById(regNo);
        if(student==null){
            return null;
        }

        Student responseStudent = student.get();
        responseStudent.getLibraryCard().setCardstatus(CardStatus.Active);
        studentRepository.save(responseStudent);
        return responseStudent;
    }
}
