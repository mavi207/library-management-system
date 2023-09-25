package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.dto.requestDTO.AuthorRequest;
import com.example.librarymanagementsystem.dto.responseDTO.AuthorResponse;
import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.librarymanagementsystem.transformer.AuthorTransformer.AuthorRequestToAuthor;
import static com.example.librarymanagementsystem.transformer.AuthorTransformer.AuthorToAuthorResponse;
import static com.example.librarymanagementsystem.transformer.BookTransformer.BookListToStringList;

@Service
public class AuthorServiceImpl {

    @Autowired
    AuthorRepository authorRepository;
    public AuthorResponse addAuthor(AuthorRequest authorRequest) {
        Author author = AuthorRequestToAuthor(authorRequest);
        Author savedAuthor  = authorRepository.save(author);
        AuthorResponse authorResponse = AuthorToAuthorResponse(savedAuthor);

        return authorResponse;
    }

    public AuthorResponse updateEmailId(int id, String email) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if(authorOptional.isEmpty()){
            throw  new AuthorNotFoundException("Author not found");
        }
        Author author = authorOptional.get();
        author.setEmail(email);
        authorRepository.save(author);
        return AuthorToAuthorResponse(author);
    }

    public List<String> booksNamesByAuthorId(int id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if(authorOptional.isEmpty()){
            throw  new AuthorNotFoundException("Author not found");
        }
        Author author = authorOptional.get();
        List<String> books = BookListToStringList(author.getBooks());

        return books;
    }

    public List<AuthorResponse> authorsHavingMoreThanXPages(int count) {
        List<Author> authors = authorRepository.authorsHavingMoreThanXPages(count);
        List<AuthorResponse> authorNames = new ArrayList<>();

        for (Author author : authors) {
            authorNames.add(AuthorToAuthorResponse(author));
        }
        return authorNames;
    }
}