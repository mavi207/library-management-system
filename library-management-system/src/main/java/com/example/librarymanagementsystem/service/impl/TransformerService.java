package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.Enum.BookStatus;
import com.example.librarymanagementsystem.Enum.TransactionStatus;
import com.example.librarymanagementsystem.dto.responseDTO.IssueBookResponse;
import com.example.librarymanagementsystem.exception.BookNotFoundException;
import com.example.librarymanagementsystem.exception.StudentNotFoundException;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.model.Transaction;

import java.util.Optional;
import java.util.UUID;

public interface TransformerService {
    public IssueBookResponse issueBook(int bookId, int studentId);

}
