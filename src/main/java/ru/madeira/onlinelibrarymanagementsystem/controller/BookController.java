package ru.madeira.onlinelibrarymanagementsystem.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.madeira.onlinelibrarymanagementsystem.criteria.BookSearchCriteria;
import ru.madeira.onlinelibrarymanagementsystem.dto.*;
import ru.madeira.onlinelibrarymanagementsystem.service.BookCopyService;
import ru.madeira.onlinelibrarymanagementsystem.service.BookService;
import ru.madeira.onlinelibrarymanagementsystem.service.UserHistoryService;
import ru.madeira.onlinelibrarymanagementsystem.specification.BookSpecification;

import javax.xml.bind.JAXBException;
import java.util.List;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookCopyService bookCopyService;
    private final UserHistoryService userHistoryService;

    //добавить сортировку и фильтрацию по жанру, по автору, по названию, по году написания и тд
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDTO> getAllBooks(@RequestParam(required = false, name = "word") String word,
                                     @RequestParam(required = false) String genre,
                                     @RequestParam(required = false) Long minYear,
                                     @RequestParam(required = false) Long maxYear,
                                     @RequestParam(name = "size", value = "size", required = false, defaultValue = "10") Integer size,
                                     @RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
        return bookService.getAllBooks(BookSpecification.getBookSpecification(new BookSearchCriteria(word, genre, minYear, maxYear)), PageRequest.of(page, size));
    }

    @PostMapping
    public BookDTO createBook(@RequestBody BookDTO book) {
        return bookService.advancedCreateBook(book);
    }

    @GetMapping(path = "/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDTO getBookById(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);
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

    @PostMapping("/updateBooksInLibrary")
    public void getBooksXmlFromBookSupplier(@RequestBody String bookXml) throws JAXBException {
        bookService.parseXmlAndAddBooks(bookXml);
    }
}
