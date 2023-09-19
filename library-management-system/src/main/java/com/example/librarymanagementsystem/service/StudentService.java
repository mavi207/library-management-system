package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.CardStatus;
import com.example.librarymanagementsystem.Enum.Gender;
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

    public Student addStudent(Student student) {
        LibraryCard libraryCard=new LibraryCard();
        libraryCard.setCardNo(String.valueOf(UUID.randomUUID()));
        libraryCard.setCardstatus(CardStatus.Active);
        libraryCard.setStudent(student);
        student.setLibraryCard(libraryCard);// set librarycard for student
        Student savedStudent = studentRepository.save(student);// save both student and library card
        return savedStudent;
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
}
