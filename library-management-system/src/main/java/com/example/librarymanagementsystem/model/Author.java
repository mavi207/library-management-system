package com.example.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    int age;

    @Column(name = "Email_id",unique=true,nullable = false)
    String email;

    @UpdateTimestamp
    Date lastactivity;

    @OneToMany(mappedBy = "Book" , cascade = CascadeType.ALL)
    ArrayList<Book> books = new ArrayList<Book>();
}