package ru.madeira.onlinelibrarymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.madeira.onlinelibrarymanagementsystem.dto.*;
import ru.madeira.onlinelibrarymanagementsystem.entity.BookCopy;
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
//добавить сортировку и фильтрацию по жанру, по автору, по названию, по году написания и тд
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDTO> getAllBooks(@RequestParam(value = "size", required = false, defaultValue = "2") Integer size,
                                     @RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
        return bookService.getAllBooks(PageRequest.of(page, size));
    }

    @PostMapping
    public BookDTO createBook(@RequestBody BookDTO book) {
        return bookService.advancedCreateBook(book);
    }

    @GetMapping("/{authorName}")
    public List<BookDTO> getBooksByAuthor(@PathVariable String authorName) {
        return bookService.findBooksByAuthor(authorName);
    }

    @GetMapping("/{bookId}/lend")
    public void lendBook(@PathVariable Long bookId) {
        bookCopyService.lendBookCopy(bookId);
    }

    // переделать списание экземпляра книги

    @DeleteMapping
    public void scrapBookCopyByBookCopyId(@RequestParam Long bookCopyId) {
        bookCopyService.scrapBookCopyByBookCopyId(bookCopyId);
    }

    @PostMapping("/{bookId}/addTags")
    public void addTagsToBook(@PathVariable Long bookId, @RequestBody List<TagDTO> tags) {
        bookService.addTagsToBook(bookId, tags);
    }

    @GetMapping("/{bookId}/getAllCopies")
    public List<BookCopyDTO> getAllCopiesByBookId(@PathVariable Long bookId) {
        return bookService.getAllCopiesByBookId(bookId);
    }

    @GetMapping("/{bookCopyId}/takingHistory")
    public List<UserHistoryDTO> getUsersWhoTookBook(@PathVariable Long bookCopyId) {
        return userHistoryService.getUsersWhoTookBookCopy(bookCopyId);
    }

    @PutMapping("/{bookId}/editBookInfo")
    public void editBookInfo(@PathVariable Long bookId, @RequestBody BookDTO bookDTO) {
        bookService.editBookInfo(bookId, bookDTO);
    }

    @PostMapping("/{bookId}/addBookCopies")
    public void addBookCopiesForBook(@PathVariable Long bookId, @RequestBody List<BookCopyDTO> bookCopies) {
        bookCopyService.addBookCopiesForBook(bookId, bookCopies);
    }
}
