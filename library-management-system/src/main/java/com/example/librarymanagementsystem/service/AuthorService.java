package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.dto.requestDTO.AuthorRequestDTO;
import com.example.librarymanagementsystem.dto.responseDTO.AuthorResponseDTO;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    public AuthorResponseDTO addAuthor(AuthorRequestDTO AuthorRequestDTO) {

        Author author = Author.builder()
                .name(AuthorRequestDTO.getName())
                .age(AuthorRequestDTO.getAge())
                .email(AuthorRequestDTO.getEmail())
                .build();

        Author responceAuthor = authorRepository.save(author);

        AuthorResponseDTO authorResponseDTO = AuthorResponseDTO.builder()
                .name(responceAuthor.getName())
                .age(responceAuthor.getAge())
                .email(responceAuthor.getEmail())
                .lastActivity(responceAuthor.getLastActivity())
                .build();

        return authorResponseDTO;
    }
}
