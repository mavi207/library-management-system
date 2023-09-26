package com.example.librarymanagementsystem.model;

import com.example.librarymanagementsystem.Enum.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    // @GeneratedValue(strategy = GenerationType.UUID)
    String transactionId; // generate using UUID this is unique transactionId

    @CreationTimestamp
    Date transactionTime;

    TransactionStatus transactionStatus;

    @ManyToOne
    @JoinColumn
    Book book;

    @ManyToOne
    @JoinColumn
    LibraryCard libraryCard;
}
