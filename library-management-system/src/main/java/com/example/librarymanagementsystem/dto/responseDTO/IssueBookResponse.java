package com.example.librarymanagementsystem.dto.responseDTO;

import com.example.librarymanagementsystem.Enum.TransactionStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@FieldDefaults( level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueBookResponse {

    String transactionNumber;

    Date transactionTime;

    TransactionStatus transactionStatus;

    String bookName;

    String authorName;

    String studentName;

    String libraryCardNumber;

}
