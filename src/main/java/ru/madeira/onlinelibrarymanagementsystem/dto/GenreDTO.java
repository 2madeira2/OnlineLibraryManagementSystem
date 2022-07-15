package ru.madeira.onlinelibrarymanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.madeira.onlinelibrarymanagementsystem.entity.Book;

import java.util.List;

@Data
@AllArgsConstructor
public class GenreDTO {
    private Long id;
    private String name;
    private List<Book> books;
}
