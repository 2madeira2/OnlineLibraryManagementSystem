package ru.madeira.onlinelibrarymanagementsystem.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.madeira.onlinelibrarymanagementsystem.dto.BookDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.Book;
import ru.madeira.onlinelibrarymanagementsystem.mapper.BookMapper;
import ru.madeira.onlinelibrarymanagementsystem.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDTO> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .stream().map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDTO createBook(BookDTO book) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toBook(book)));
    }

    public List<BookDTO> findBooksByAuthor(String authorName) {
        List<Book> books = new ArrayList<>(bookRepository.findBooksByAuthorsNameContains(authorName));
        books.addAll(bookRepository.findBooksByAuthorsSurnameContains(authorName));
        books.addAll(bookRepository.findBooksByAuthorsPatronymicContains(authorName));
        return bookMapper.toDto(books);
    }

}
