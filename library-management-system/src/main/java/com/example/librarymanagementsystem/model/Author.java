package com.example.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(unique = true, nullable = false)
    String name;

    int age;

    @Column(name = "Email_id",unique=true,nullable = false)
    String email;

    @UpdateTimestamp
    Date lastActivity;

    @OneToMany(mappedBy = "author" , cascade = CascadeType.ALL)
    List<Book> books = new ArrayList<>();
}
