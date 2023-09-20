package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponseDTO;
import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.exception.BookNotFoundException;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    public List<BookResponseDTO> bookOfGenre(Genre genre) {
        List<Book> books = bookRepository.findByGenre(genre);

        List<BookResponseDTO> bookResponseDTOS = new ArrayList<>();

        for(Book book : books){
            BookResponseDTO bookResponseDTO = new BookResponseDTO();

            bookResponseDTO.setName(book.getName());
            bookResponseDTO.setNumberOfPages(book.getNumberOfPages());
            bookResponseDTO.setGenre(book.getGenre());
            bookResponseDTO.setCost(book.getCost());
            bookResponseDTO.setIssued(book.getIssued());
            bookResponseDTO.setAuthor(book.getAuthor().getName());

            bookResponseDTOS.add(bookResponseDTO);
        }
        return bookResponseDTOS;
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

    public List<BookResponseDTO> getBooksByGenreAndGreaterThanCost(String genre, double cost) {
        List<Book> books = bookRepository.getBooksByGenreAndGreaterThanCost(genre,cost);

        List<BookResponseDTO> bookResponseDTOS = new ArrayList<>();
        for(Book book : books){
            BookResponseDTO bookResponseDTO = new BookResponseDTO();
            bookResponseDTO.setName(book.getName());
            bookResponseDTO.setNumberOfPages(book.getNumberOfPages());
            bookResponseDTO.setGenre(book.getGenre());
            bookResponseDTO.setCost(book.getCost());
            bookResponseDTO.setIssued(book.getIssued());
            bookResponseDTO.setAuthor(book.getAuthor().getName());
        }
        return bookResponseDTOS;
    }

    public List<BookResponseDTO> getBooksBetweenPages(int minPage, int maxPage) {
        List<Book> books = bookRepository.getBooksBetweenPages(minPage,maxPage);

        List<BookResponseDTO> bookResponseDTOS = new ArrayList<>();
        for(Book book : books){
            BookResponseDTO bookResponseDTO = new BookResponseDTO();
            bookResponseDTO.setName(book.getName());
            bookResponseDTO.setNumberOfPages(book.getNumberOfPages());
            bookResponseDTO.setGenre(book.getGenre());
            bookResponseDTO.setCost(book.getCost());
            bookResponseDTO.setIssued(book.getIssued());
            bookResponseDTO.setAuthor(book.getAuthor().getName());
        }
        return bookResponseDTOS;
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
