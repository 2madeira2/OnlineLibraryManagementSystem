package ru.madeira.onlinelibrarymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.madeira.onlinelibrarymanagementsystem.dto.AuthorDTO;
import ru.madeira.onlinelibrarymanagementsystem.dto.BookDTO;
import ru.madeira.onlinelibrarymanagementsystem.service.AuthorService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public void setAuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping(value = "/{authorId}/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<BookDTO> getAllBooksByAuthor(@PathVariable Long authorId) {
        return authorService.getAllBooksByAuthor(authorId);
    }
}
