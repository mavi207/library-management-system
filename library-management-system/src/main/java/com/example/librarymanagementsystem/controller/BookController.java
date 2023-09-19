package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public String addBook(@RequestBody Book book){
        try{
            String response = bookService.addBook(book);
            return response;
        }
        catch(Exception e){
            return e.getMessage();
        }
    }

    // delete a book

    // give me names of all the books of a particular genre

    // give me names of all the books of a particular genre and cost greater than 500 rs

    // give me all the books having number of pages between 'a' and 'b'

    // give me the names of all the authors who write a particular genre



}
