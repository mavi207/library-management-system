package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponse;
import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.exception.BookNotFoundException;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    public List<BookResponse> bookOfGenre(Genre genre) {
        List<Book> books = bookRepository.findByGenre(genre);

        List<BookResponse> bookResponses = new ArrayList<>();

        for(Book book : books){
            BookResponse bookResponse = new BookResponse();

            bookResponse.setName(book.getName());
            bookResponse.setNumberOfPages(book.getNumberOfPages());
            bookResponse.setGenre(book.getGenre());
            bookResponse.setCost(book.getCost());
            bookResponse.setIssued(book.getIssued());
            bookResponse.setAuthor(book.getAuthor().getName());

            bookResponses.add(bookResponse);
        }
        return bookResponses;
    }

    public String addBook(Book book) {
        Optional<Author> authorOptional = authorRepository.findById(book.getAuthor().getId());
        if(authorOptional.isEmpty()){
            throw new AuthorNotFoundException("Invalid author id!!!");
        }
        Author author = authorOptional.get();
        book.setAuthor(author);
        author.getBooks().add(book);


        authorRepository.save(author);// save both author and book
        return "Book added successfully";
    }

    public List<BookResponse> getBooksByGenreAndGreaterThanCost(String genre, double cost) {
        List<Book> books = bookRepository.getBooksByGenreAndGreaterThanCost(genre,cost);

        List<BookResponse> bookResponses = new ArrayList<>();
        for(Book book : books){
            BookResponse bookResponse = new BookResponse();
            bookResponse.setName(book.getName());
            bookResponse.setNumberOfPages(book.getNumberOfPages());
            bookResponse.setGenre(book.getGenre());
            bookResponse.setCost(book.getCost());
            bookResponse.setIssued(book.getIssued());
            bookResponse.setAuthor(book.getAuthor().getName());
        }
        return bookResponses;
    }

    public List<BookResponse> getBooksBetweenPages(int minPage, int maxPage) {
        List<Book> books = bookRepository.getBooksBetweenPages(minPage,maxPage);

        List<BookResponse> bookResponses = new ArrayList<>();
        for(Book book : books){
            BookResponse bookResponse = new BookResponse();
            bookResponse.setName(book.getName());
            bookResponse.setNumberOfPages(book.getNumberOfPages());
            bookResponse.setGenre(book.getGenre());
            bookResponse.setCost(book.getCost());
            bookResponse.setIssued(book.getIssued());
            bookResponse.setAuthor(book.getAuthor().getName());
        }
        return bookResponses;
    }


    public String deleteBook(int bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isPresent()){
            Book book = bookOptional.get();
            Author author = book.getAuthor();

            author.getBooks().remove(book);

            bookRepository.delete(book);

            return "Book successfully deleted..";
        }
        else {
            throw new BookNotFoundException("Book not found with ID: "+bookId);
        }
    }
}
