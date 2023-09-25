package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.dto.requestDTO.AuthorRequest;
import com.example.librarymanagementsystem.dto.responseDTO.AuthorResponse;
import com.example.librarymanagementsystem.model.Author;

public class AuthorTransformer {
    public static Author AuthorRequestToAuthor(AuthorRequest authorRequest){
        return Author.builder()
                .name(authorRequest.getName())
                .age(authorRequest.getAge())
                .email(authorRequest.getEmail())
                .build();
    }

    public static AuthorResponse AuthorToAuthorResponse(Author author){
        return AuthorResponse.builder()
                .name(author.getName())
                .age(author.getAge())
                .email(author.getEmail())
                .lastActivity(author.getLastActivity())
                .build();
    }
}
