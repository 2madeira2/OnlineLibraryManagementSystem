package ru.madeira.onlinelibrarymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import ru.madeira.onlinelibrarymanagementsystem.dto.BookDTO;
import ru.madeira.onlinelibrarymanagementsystem.dto.TagDTO;
import ru.madeira.onlinelibrarymanagementsystem.service.BookCopyService;
import ru.madeira.onlinelibrarymanagementsystem.service.BookService;
import ru.madeira.onlinelibrarymanagementsystem.service.UserHistoryService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private BookCopyService bookCopyService;
    private UserHistoryService userHistoryService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setBookCopyService(BookCopyService bookCopyService) {
        this.bookCopyService = bookCopyService;
    }

    @Autowired
    public void setUserHistoryService(UserHistoryService userHistoryService) {
        this.userHistoryService = userHistoryService;
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

    @GetMapping("/{bookId}/lend")
    public void lendBook(@PathVariable Long bookId) {
        bookCopyService.lendBookCopy(bookId);
    }

    @DeleteMapping
    public void scrapBookCopyByBookCopyId(@RequestParam Long bookCopyId) {
        bookCopyService.scrapBookCopyByBookCopyId(bookCopyId);
    }

    @PostMapping("/{bookId}/addTags")
    public void addTagsToBook(@PathVariable Long bookId, @RequestBody List<TagDTO> tags) {
        bookService.addTagsToBook(bookId, tags);
    }


}
