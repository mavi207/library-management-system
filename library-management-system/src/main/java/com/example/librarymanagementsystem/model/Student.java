package com.example.librarymanagementsystem.model;

import com.example.librarymanagementsystem.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level=AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Student_info")
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int regNo;

    @Column(name="student_name")
    String name;

    int age;

    @Column(unique=true,nullable = false)
    String email;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @OneToOne(mappedBy = "student" , cascade = CascadeType.ALL)
    LibraryCard libraryCard;
}
