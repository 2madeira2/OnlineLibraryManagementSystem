package ru.madeira.onlinelibrarymanagementsystem.service;

import org.springframework.stereotype.Service;
import ru.madeira.onlinelibrarymanagementsystem.entity.BookCopy;
import ru.madeira.onlinelibrarymanagementsystem.repository.BookCopyRepository;

@Service
public class BookCopyService {

    private final BookCopyRepository bookCopyRepository;

    public BookCopyService(BookCopyRepository bookCopyRepository) {
        this.bookCopyRepository = bookCopyRepository;
    }

    public void lendBookCopy(Long bookCopyId) {
        bookCopyRepository.updateBookCopyStatus(bookCopyId);
    }

    public void releaseBookCopy(Long bookCopyId) {
        BookCopy bookCopy = bookCopyRepository.getBookCopyById(bookCopyId).get();
        bookCopy.setIsBusy(false);
        bookCopyRepository.save(bookCopy);
    }

    public void scrapBookCopyByBookCopyId(Long bookCopyId) {
        bookCopyRepository.deleteById(bookCopyId);
    }
}
