package ru.madeira.onlinelibrarymanagementsystem.service;

import org.springframework.stereotype.Service;
import ru.madeira.onlinelibrarymanagementsystem.exception.FreeBookCopiesNotFoundException;
import ru.madeira.onlinelibrarymanagementsystem.repository.BookCopyRepository;

@Service
public class BookCopyService {

    private final BookCopyRepository bookCopyRepository;

    public BookCopyService(BookCopyRepository bookCopyRepository) {
        this.bookCopyRepository = bookCopyRepository;
    }

    public void lendBookCopy(Long bookId) {
        Long bookCopyId = bookCopyRepository.getBookCopyIdByBookIdAndIsBusyIsFalse(bookId).orElseThrow(FreeBookCopiesNotFoundException::new);
        bookCopyRepository.updateBookCopyStatus(bookCopyId);
    }
}
