package ru.madeira.onlinelibrarymanagementsystem.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.madeira.onlinelibrarymanagementsystem.dto.GenreDTO;
import ru.madeira.onlinelibrarymanagementsystem.service.GenreService;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GenreDTO> getAllGenres() {
        return genreService.getAllGenres();
    }

    @PostMapping
    public GenreDTO createNewGenre(@RequestBody GenreDTO genre) {
        return genreService.createNewGenre(genre);
    }

    @PutMapping("/{genreId}/edit")
    public void editGenreInfo(@RequestBody GenreDTO genreDTO, @PathVariable Long genreId) {
        genreService.editGenreInfo(genreId, genreDTO);
    }
}
