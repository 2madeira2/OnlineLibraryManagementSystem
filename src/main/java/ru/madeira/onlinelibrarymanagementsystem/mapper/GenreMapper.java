package ru.madeira.onlinelibrarymanagementsystem.mapper;

import org.mapstruct.Mapper;
import ru.madeira.onlinelibrarymanagementsystem.dto.GenreDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.Genre;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreDTO toDto(Genre genre);
    List<GenreDTO> toDto(List<Genre> genres);
}
