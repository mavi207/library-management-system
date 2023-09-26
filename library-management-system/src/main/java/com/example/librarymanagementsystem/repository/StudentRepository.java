package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.Enum.Gender;
import com.example.librarymanagementsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    List<Student> findByGender(Gender gender);

    Student findByEmail(String email);

    // @Query (value = "select * from student_info where email = : email and gender = : gender " , nativeQuery = true)//sql
    Student findByEmailAndGender(String email, Gender gender);
}
