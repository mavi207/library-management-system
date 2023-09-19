package com.example.librarymanagementsystem.model;

import com.example.librarymanagementsystem.Enum.BookStatus;
import com.example.librarymanagementsystem.Enum.Genre;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(unique = true , nullable = false)
    String name;

    int numberOfPages;

    @Enumerated(EnumType.STRING)
    Genre genre;

    double cost;

    @Enumerated(value = EnumType.STRING)
    BookStatus issued; // to check weather book is issued or not at present time

    @ManyToOne
    @JoinColumn
    Author author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    List<Transaction> transactions = new ArrayList<>();
}
