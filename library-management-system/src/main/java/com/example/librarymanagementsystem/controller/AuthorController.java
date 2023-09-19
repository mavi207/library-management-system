package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestParam Author author){
        String response = authorService.addAuthor(author);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



    // update the email id of an author  -->> observer lastActivity column

    // Give me the names of all the books written by a partiular author

    // give me the names of authors who have written more than 'x' number of books
}