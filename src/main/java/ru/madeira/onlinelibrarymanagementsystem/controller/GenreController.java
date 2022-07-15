package ru.madeira.onlinelibrarymanagementsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.madeira.onlinelibrarymanagementsystem.dto.GenreDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.Genre;
import ru.madeira.onlinelibrarymanagementsystem.service.GenreService;

import java.util.List;

@RestController("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<GenreDTO> getAllGenres() {
        return genreService.getAllGenres();
    }
}
