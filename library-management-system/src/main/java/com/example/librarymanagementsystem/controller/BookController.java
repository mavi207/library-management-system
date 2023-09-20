package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponseDTO;
import com.example.librarymanagementsystem.exception.BookNotFoundException;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @DeleteMapping("/delete")
    public ResponseEntity deleteBook(@RequestParam("id") int bookId){
        try{
            String response = bookService.deleteBook(bookId);
            return new ResponseEntity(response,HttpStatus.ACCEPTED);
        }
        catch (BookNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // give me names of all the books of a particular genre
    @GetMapping("/books-genre")
    public List<BookResponseDTO> bookOfGenre(@RequestParam("genre") Genre genre){
        List<BookResponseDTO> books = bookService.bookOfGenre(genre);
        return books;
    }

    // give me names of all the books of a particular genre and cost greater than x rs
    @GetMapping("/get-by-genre-cost")
    public List<BookResponseDTO> getBooksByGenreAndGreaterThanCost(@RequestParam("genre") String genre, @RequestParam("cost") double cost)// this is implemented using SQL
    {
        return bookService.getBooksByGenreAndGreaterThanCost(genre,cost);
    }

    // give me all the books having number of pages between 'a' and 'b'
    @GetMapping("/get-between-pages")
    public List<BookResponseDTO> getBooksBetweenPages(@RequestParam("minpage") int minPage, @RequestParam("maxpage") int maxPage)// this is implemented using HQL
    {
        return bookService.getBooksBetweenPages(minPage,maxPage);
    }

    // give me the names of all the authors who write a particular genre



}
