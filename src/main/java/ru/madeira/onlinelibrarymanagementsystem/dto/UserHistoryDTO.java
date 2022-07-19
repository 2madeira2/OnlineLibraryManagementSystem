package ru.madeira.onlinelibrarymanagementsystem.dto;

import lombok.Data;
import ru.madeira.onlinelibrarymanagementsystem.entity.BookCopy;

import java.time.LocalDate;

@Data
public class UserHistoryDTO {
    private BookCopy bookCopy;
    private LocalDate receiptDate;
    private LocalDate releaseDate;
    private LocalDate returnDate;
}
