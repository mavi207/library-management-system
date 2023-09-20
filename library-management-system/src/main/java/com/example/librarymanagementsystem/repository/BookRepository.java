package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponseDTO;
import com.example.librarymanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query(value = "SELECT * FROM book WHERE genre = : genre AND cost > : cost" , nativeQuery = true)// this is implemented using SQL
    List<Book> getBooksByGenreAndGreaterThanCost(String genre, double cost);

    @Query(value = "select b from Book b where b.numberOfPages > : minPage and b.numberOfPages < : maxPage")// this is implemented using HQL, here b is reference of Book
    List<Book> getBooksBetweenPages(int minPage, int maxPage);


    List<Book> findByGenre(Genre genre);
}
