package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.dto.requestDTO.AuthorRequest;
import com.example.librarymanagementsystem.dto.responseDTO.AuthorResponse;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.librarymanagementsystem.transformer.AuthorTransformer.AuthorRequestToAuthor;
import static com.example.librarymanagementsystem.transformer.AuthorTransformer.AuthorToAuthorResponse;

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
}
