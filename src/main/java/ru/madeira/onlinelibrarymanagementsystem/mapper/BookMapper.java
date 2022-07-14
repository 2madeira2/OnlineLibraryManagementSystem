package ru.madeira.onlinelibrarymanagementsystem.mapper;

import org.mapstruct.Mapper;
import ru.madeira.onlinelibrarymanagementsystem.dto.BookDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.Book;

import java.util.List;

@Mapper
public interface BookMapper {
    BookDTO toDto(Book book);
    Book toBook(BookDTO book);
    List<BookDTO> toDto(List<Book> books);
}
