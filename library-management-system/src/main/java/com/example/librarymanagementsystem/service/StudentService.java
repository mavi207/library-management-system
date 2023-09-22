package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.CardStatus;
import com.example.librarymanagementsystem.Enum.Gender;
import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;
import com.example.librarymanagementsystem.model.LibraryCard;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public StudentResponse addStudent(StudentRequest studentRequest) {

        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setAge(studentRequest.getAge());
        student.setEmail(studentRequest.getEmail());
        student.setGender(studentRequest.getGender());

        LibraryCard libraryCard=new LibraryCard();
        libraryCard.setCardNo(String.valueOf(UUID.randomUUID()));
        libraryCard.setCardstatus(CardStatus.Active);
        libraryCard.setStudent(student);
        student.setLibraryCard(libraryCard);// set library card for student
        Student savedStudent = studentRepository.save(student);// save both student and library card

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setName(savedStudent.getName());
        studentResponse.setEmail(savedStudent.getEmail());
        studentResponse.setMessage("Task Completed..");
        studentResponse.setCardIssueNumber(savedStudent.getLibraryCard().getCardNo());

        return studentResponse;
    }

    public Student getStudent(int regNo) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if (!studentOptional.isPresent()) {
            return null;
        }
        return studentOptional.get();
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

    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students;
    }

    public Student findByEmail(String email) {
        Student student = studentRepository.findByEmail(email);
        return student;
    }

    public Student findByEmailandGender(String email, Gender gender) {
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
