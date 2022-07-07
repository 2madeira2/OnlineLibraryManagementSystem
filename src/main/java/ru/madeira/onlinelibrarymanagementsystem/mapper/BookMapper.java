package ru.madeira.onlinelibrarymanagementsystem.mapper;

import org.springframework.stereotype.Component;
import ru.madeira.onlinelibrarymanagementsystem.dto.BookDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.Book;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    public List<BookDTO> toDto(List<Book> books) {
        return books.stream()
                .map(b -> new BookDTO(b.getId(), b.getTitle(), b.getYear(), b.getDescription(), b.getAuthors(), b.getGenres(), b.getTags(), b.getBookCopies()))
                .collect(Collectors.toList());
    }
}
