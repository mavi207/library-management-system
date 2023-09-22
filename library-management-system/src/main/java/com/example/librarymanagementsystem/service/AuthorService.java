package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.dto.requestDTO.AuthorRequest;
import com.example.librarymanagementsystem.dto.responseDTO.AuthorResponse;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    public AuthorResponse addAuthor(AuthorRequest authorRequest) {

        Author author = Author.builder()
                .name(authorRequest.getName())
                .age(authorRequest.getAge())
                .email(authorRequest.getEmail())
                .build();

        Author responceAuthor = authorRepository.save(author);

        AuthorResponse authorResponse = AuthorResponse.builder()
                .name(responceAuthor.getName())
                .age(responceAuthor.getAge())
                .email(responceAuthor.getEmail())
                .lastActivity(responceAuthor.getLastActivity())
                .build();

        return authorResponse;
    }
}
