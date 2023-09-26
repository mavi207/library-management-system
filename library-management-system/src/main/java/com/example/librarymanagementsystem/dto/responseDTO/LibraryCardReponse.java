package com.example.librarymanagementsystem.dto.responseDTO;

import com.example.librarymanagementsystem.Enum.CardStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LibraryCardReponse {

    String cardNumber;

    CardStatus cardStatus;

    Data issueDate;
}
