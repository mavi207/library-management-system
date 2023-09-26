package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.Enum.BookStatus;
import com.example.librarymanagementsystem.Enum.TransactionStatus;
import com.example.librarymanagementsystem.dto.responseDTO.IssueBookResponse;
import com.example.librarymanagementsystem.exception.BookNotFoundException;
import com.example.librarymanagementsystem.exception.StudentNotFoundException;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.model.Transaction;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.StudentRepository;
import com.example.librarymanagementsystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransformerService{

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public IssueBookResponse issueBook(int bookId, int studentId){

        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isEmpty()){
            throw new StudentNotFoundException("Invalid student id!!");
        }

        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isEmpty()){
            throw new BookNotFoundException("Invalid book id!!");
        }

        Book book = bookOptional.get();

        if(book.getIssued()== BookStatus.Issued){
            throw new BookNotFoundException("Book is Already issued..");
        }

        // issue the book
        Student student = studentOptional.get();

        // create transaction
        Transaction transaction = Transaction.builder()
                .transactionId(String.valueOf(UUID.randomUUID()))
                .transactionStatus(TransactionStatus.Success)
                .book(book)
                .libraryCard(student.getLibraryCard())
                .build();

        Transaction savedTransaction = transactionRepository.save(transaction);

        // update book
        book.setIssued(BookStatus.Issued);
        book.getTransactions().add(savedTransaction);

        // card changes
        student.getLibraryCard().getTransactions().add(savedTransaction);

        Book savedBook = bookRepository.save(book);  // book and transaction
        Student savedStudent = studentRepository.save(student);  // student and transaction

        return IssueBookResponse.builder()
                .bookName(savedBook.getName())
                .transactionStatus(savedTransaction.getTransactionStatus())
                .transactionTime(savedTransaction.getTransactionTime())
                .transactionNumber(savedTransaction.getTransactionId())
                .studentName(savedStudent.getName())
                .libraryCardNumber(savedStudent.getLibraryCard().getCardNo())
                .authorName(savedBook.getAuthor().getName())
                .build();
    }
}
