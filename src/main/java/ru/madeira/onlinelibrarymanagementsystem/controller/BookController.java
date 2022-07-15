package ru.madeira.onlinelibrarymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.madeira.onlinelibrarymanagementsystem.dto.BookDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.BookCopy;
import ru.madeira.onlinelibrarymanagementsystem.service.BookCopyService;
import ru.madeira.onlinelibrarymanagementsystem.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private BookCopyService bookCopyService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setBookCopyService(BookCopyService bookCopyService) {
        this.bookCopyService = bookCopyService;
    }

    @GetMapping()
    public List<BookDTO> getAllBooks(@RequestParam(value = "size", required = false, defaultValue = "2") Integer size,
                                     @RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
        return bookService.getAllBooks(PageRequest.of(page, size));
    }

    @PostMapping
    public BookDTO createBook(@RequestBody BookDTO book) {
        return bookService.createBook(book);
    }

    @GetMapping("/{authorName}")
    public List<BookDTO> getBooksByAuthor(@PathVariable String authorName) {
        return bookService.findBooksByAuthor(authorName);
    }

    @PostMapping("/{bookId}/lend")
    public void lendBook(@PathVariable Long bookId, @RequestParam Long readersTicketNumber) {
        bookCopyService.lendBookCopy(bookId);

        //TODO: ЗАПИСЬ О ТОМ, ЧТО ПОЛЬЗОВАТЕЛЮ ВЫДАНА КНИГА В ИСТОРИЮ ПОЛЬЗОВАТЕЛЯ
    }

    

}
