package ru.madeira.onlinelibrarymanagementsystem.service;

import org.springframework.stereotype.Service;
import ru.madeira.onlinelibrarymanagementsystem.dto.AuthorDTO;
import ru.madeira.onlinelibrarymanagementsystem.dto.BookDTO;
import ru.madeira.onlinelibrarymanagementsystem.exception.AuthorNotFoundException;
import ru.madeira.onlinelibrarymanagementsystem.mapper.AuthorMapper;
import ru.madeira.onlinelibrarymanagementsystem.mapper.BookMapper;
import ru.madeira.onlinelibrarymanagementsystem.repository.AuthorRepository;

import java.util.List;
import java.util.Set;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper, BookMapper bookMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
        this.bookMapper = bookMapper;
    }

    public List<AuthorDTO> getAllAuthors() {
        return authorMapper.toDto(authorRepository.findAll());
    }

    public Set<BookDTO> getAllBooksByAuthor(Long authorId) {
        return bookMapper.toDto(authorRepository.findById(authorId).orElseThrow(AuthorNotFoundException::new).getBooks());
    }
}
