package ru.madeira.onlinelibrarymanagementsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.madeira.onlinelibrarymanagementsystem.entity.Book;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @GetMapping
    public List<Book> getAllBooks() {
        return new ArrayList<>();
    }
}
