package ru.madeira.onlinelibrarymanagementsystem.dto;

import lombok.Data;
import ru.madeira.onlinelibrarymanagementsystem.entity.Author;
import ru.madeira.onlinelibrarymanagementsystem.entity.BookCopy;
import ru.madeira.onlinelibrarymanagementsystem.entity.Genre;
import ru.madeira.onlinelibrarymanagementsystem.entity.Tag;

import java.util.Set;

@Data
public class BookDTO {
    private String title;
    private Integer year;
    private String description;
    private Set<Author> authors;
    private Set<Genre> genres;
    private Set<Tag> tags;
    private Set<BookCopy> bookCopies;
}
