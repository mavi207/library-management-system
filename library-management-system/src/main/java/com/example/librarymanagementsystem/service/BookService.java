package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponse;
import com.example.librarymanagementsystem.model.Book;

import java.util.List;

public interface BookService {
    public List<BookResponse> bookOfGenre(Genre genre);

    public String addBook(Book book);

    public List<BookResponse> getBooksByGenreAndGreaterThanCost(String genre, double cost);

    public List<BookResponse> getBooksBetweenPages(int minPage, int maxPage);


    public String deleteBook(int bookId);
}