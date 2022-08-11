package ru.madeira.onlinelibrarymanagementsystem.service;

import org.springframework.stereotype.Service;
import ru.madeira.onlinelibrarymanagementsystem.dto.GenreDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.Genre;
import ru.madeira.onlinelibrarymanagementsystem.exception.GenreAlreadyExistsInSystemException;
import ru.madeira.onlinelibrarymanagementsystem.exception.GenreNotFoundException;
import ru.madeira.onlinelibrarymanagementsystem.mapper.GenreMapper;
import ru.madeira.onlinelibrarymanagementsystem.repository.GenreRepository;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreService(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    public List<GenreDTO> getAllGenres() {
        return genreMapper.toDto(genreRepository.findAll());
    }

    public GenreDTO createNewGenre(GenreDTO genre) {
        if(genreRepository.existsGenreByName(genre.getName())) {
            throw new GenreAlreadyExistsInSystemException();
        }
        return genreMapper.toDto(genreRepository.save(genreMapper.toGenre(genre)));
    }

    public void editGenreInfo(Long genreId, GenreDTO genreDTO) {
        if(genreRepository.existsGenreById(genreId)) {
            Genre editGenre = genreMapper.toGenre(genreDTO);
            editGenre.setId(genreId);
            genreRepository.save(editGenre);
        } else {
            throw new GenreNotFoundException();
        }
    }
}
