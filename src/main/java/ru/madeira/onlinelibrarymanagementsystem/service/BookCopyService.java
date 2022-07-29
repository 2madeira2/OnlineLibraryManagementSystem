package ru.madeira.onlinelibrarymanagementsystem.service;

import org.springframework.stereotype.Service;
import ru.madeira.onlinelibrarymanagementsystem.entity.BookCopy;
import ru.madeira.onlinelibrarymanagementsystem.repository.BookCopyRepository;

import javax.transaction.Transactional;

@Service
public class BookCopyService {

    private final BookCopyRepository bookCopyRepository;
    private final UserHistoryService userHistoryService;

    public BookCopyService(BookCopyRepository bookCopyRepository, UserHistoryService userHistoryService) {
        this.bookCopyRepository = bookCopyRepository;
        this.userHistoryService = userHistoryService;
    }

    @Transactional
    public void lendBookCopy(Long bookId) {
        Long bookCopyId = userHistoryService.createNewUserHistoryRecord(bookId);
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).get();
        bookCopy.setIsBusy(true);
        bookCopyRepository.save(bookCopy);
    }

    @Transactional
    public void releaseBookCopy(Long bookCopyId) {
        BookCopy bookCopy = bookCopyRepository.getBookCopyById(bookCopyId).get();
        bookCopy.setIsBusy(false);
        bookCopyRepository.save(bookCopy);
    }

    public void scrapBookCopyByBookCopyId(Long bookCopyId) {
        bookCopyRepository.deleteById(bookCopyId);
    }
}
