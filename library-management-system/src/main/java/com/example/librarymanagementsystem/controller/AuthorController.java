package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.requestDTO.AuthorRequest;
import com.example.librarymanagementsystem.dto.responseDTO.AuthorResponse;
import com.example.librarymanagementsystem.service.impl.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorServiceImpl authorServiceImpl;

    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestParam AuthorRequest authorRequest){
        AuthorResponse response = authorServiceImpl.addAuthor(authorRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



    // update the email id of an author  -->> observer lastActivity column
    @PutMapping("/update-email")
    public ResponseEntity updateEmailId(@RequestParam("id") int id , @RequestParam String email){
        try{
            AuthorResponse response = authorServiceImpl.updateEmailId(id,email);
            return new ResponseEntity(response,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    // Give me the names of all the books written by a particular author
    @GetMapping("/get-books")
    public ResponseEntity booksNamesByAuthorId(@RequestParam("id") int id ){
        try{
            List<String> books = authorServiceImpl.booksNamesByAuthorId(id);
            return new ResponseEntity(books,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    // give me the names of authors who have written more than 'x' number of books
    @GetMapping("/get-author-more-than-x-books")
    public ResponseEntity authorsHavingMoreThanXPages(@RequestParam("count") int count){
        List<AuthorResponse> authors = authorServiceImpl.authorsHavingMoreThanXPages(count);

        return new ResponseEntity(authors,HttpStatus.ACCEPTED);
    }
}