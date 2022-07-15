package ru.madeira.onlinelibrarymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.madeira.onlinelibrarymanagementsystem.entity.Author;
import ru.madeira.onlinelibrarymanagementsystem.entity.BookCopy;
import ru.madeira.onlinelibrarymanagementsystem.entity.Genre;
import ru.madeira.onlinelibrarymanagementsystem.entity.Tag;

import java.util.Set;

@Data
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private String title;
    private Integer year;
    private String description;
    private Set<Author> authors;
    private Set<Genre> genres;
    private Set<Tag> tags;
    private Set<BookCopy> bookCopies;
}
