package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

    @Query(value = "SELECT * FROM author a WHERE (SELECT COUNT(*) FROM book b WHERE b.author_id = a.id) > :count", nativeQuery = true)
    List<Author> authorsHavingMoreThanXPages(int count);

}
