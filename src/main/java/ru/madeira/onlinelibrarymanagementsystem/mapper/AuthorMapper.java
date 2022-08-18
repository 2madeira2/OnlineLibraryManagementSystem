package ru.madeira.onlinelibrarymanagementsystem.mapper;

import org.mapstruct.Mapper;
import ru.madeira.onlinelibrarymanagementsystem.dto.AuthorDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.Author;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO toDTO(Author author);
    List<AuthorDTO> toDto(List<Author> authors);
}
