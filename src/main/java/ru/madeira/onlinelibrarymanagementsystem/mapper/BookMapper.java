package ru.madeira.onlinelibrarymanagementsystem.mapper;

import org.mapstruct.Mapper;
import ru.madeira.onlinelibrarymanagementsystem.dto.BookDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.Book;

@Mapper
public interface BookMapper {
    BookDTO toDto(Book book);
    Book toBook(BookDTO book);
}
