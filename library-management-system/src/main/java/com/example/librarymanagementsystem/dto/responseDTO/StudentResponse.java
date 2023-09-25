package com.example.librarymanagementsystem.dto.responseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class StudentResponse {
    String name;

    String email;

    String cardIssueNumber;
}
