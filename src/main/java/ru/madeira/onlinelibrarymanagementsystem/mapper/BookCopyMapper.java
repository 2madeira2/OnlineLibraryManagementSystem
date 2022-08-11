package ru.madeira.onlinelibrarymanagementsystem.mapper;

import org.mapstruct.Mapper;
import ru.madeira.onlinelibrarymanagementsystem.dto.BookCopyDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.BookCopy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookCopyMapper {
    BookCopyDTO toDTO(BookCopy bookCopy);
    List<BookCopyDTO> toDTO(List<BookCopy> bookCopies);
}
