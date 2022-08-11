package ru.madeira.onlinelibrarymanagementsystem.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserHistoryDTO {
    private BookCopyDTO bookCopy;
    private UserDTO user;
    private LocalDate receiptDate;
    private LocalDate releaseDate;
    private LocalDate returnDate;
}
