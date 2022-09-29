package ru.madeira.onlinelibrarymanagementsystem.service;

import org.springframework.stereotype.Service;
import ru.madeira.onlinelibrarymanagementsystem.dto.BookCopyDTO;
import ru.madeira.onlinelibrarymanagementsystem.dto.UserHistoryDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.Book;
import ru.madeira.onlinelibrarymanagementsystem.entity.BookCopy;
import ru.madeira.onlinelibrarymanagementsystem.entity.UserHistory;
import ru.madeira.onlinelibrarymanagementsystem.exception.BookCopyNotExistsException;
import ru.madeira.onlinelibrarymanagementsystem.exception.BookNotFoundException;
import ru.madeira.onlinelibrarymanagementsystem.exception.FreeBookCopiesNotFoundException;
import ru.madeira.onlinelibrarymanagementsystem.repository.BookCopyRepository;
import ru.madeira.onlinelibrarymanagementsystem.repository.BookRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookCopyService {

    private final BookCopyRepository bookCopyRepository;
    private final UserHistoryService userHistoryService;
    private final BookRepository bookRepository;

    public BookCopyService(BookCopyRepository bookCopyRepository, UserHistoryService userHistoryService, BookRepository bookRepository) {
        this.bookCopyRepository = bookCopyRepository;
        this.userHistoryService = userHistoryService;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void lendBookCopy(Long bookId) {
        Long bookCopyId = userHistoryService.createNewUserHistoryRecord(bookId);
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(BookCopyNotExistsException::new);
        bookCopy.setIsBusy(true);
        bookCopyRepository.save(bookCopy);
    }

    @Transactional
    public void releaseBookCopy(Long bookCopyId) {
        BookCopy bookCopy = bookCopyRepository.getBookCopyById(bookCopyId).orElseThrow(BookCopyNotExistsException::new);
        bookCopy.setIsBusy(false);
        bookCopyRepository.save(bookCopy);
    }

    public void scrapBookCopyByBookCopyId(Long bookCopyId) {
        bookCopyRepository.deleteById(bookCopyId);
    }


    public void addBookCopiesForBook(Long bookId, List<BookCopyDTO> bookCopies) {
        Book book = bookRepository.findBookById(bookId).orElseThrow(BookNotFoundException::new);
        for (BookCopyDTO bookCopyDTO : bookCopies) {
            BookCopy bookCopy = new BookCopy();
            bookCopy.setIsBusy(bookCopyDTO.getIsBusy());
            bookCopy.setBook(book);
            bookCopyRepository.save(bookCopy);
        }
    }
}
