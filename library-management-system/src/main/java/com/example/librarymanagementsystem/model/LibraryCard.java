package com.example.librarymanagementsystem.model;

import com.example.librarymanagementsystem.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="library_card")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String cardNo;

    @Enumerated(EnumType.STRING)
    @Column(name="statusOfCard")
    CardStatus cardstatus;

    @CreationTimestamp
    Date issuedate;

    @OneToOne
    @JoinColumn
    Student student;

}
