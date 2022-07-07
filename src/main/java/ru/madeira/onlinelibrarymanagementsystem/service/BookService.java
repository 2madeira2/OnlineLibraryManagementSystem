package ru.madeira.onlinelibrarymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.madeira.onlinelibrarymanagementsystem.dto.BookDTO;
import ru.madeira.onlinelibrarymanagementsystem.mapper.BookMapper;
import ru.madeira.onlinelibrarymanagementsystem.repository.BookRepository;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;
    private BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDTO> getAllBooks() {
        return bookMapper.toDto(bookRepository.findAll());
    }

}
