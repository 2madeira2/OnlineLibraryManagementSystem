package ru.madeira.onlinelibrarymanagementsystem.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.madeira.onlinelibrarymanagementsystem.dto.BookCopyDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.Book;
import ru.madeira.onlinelibrarymanagementsystem.entity.BookCopy;
import ru.madeira.onlinelibrarymanagementsystem.repository.BookCopyRepository;
import ru.madeira.onlinelibrarymanagementsystem.repository.BookRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@DisplayName(" В классе BookCopyService ")
@ExtendWith(MockitoExtension.class)
class BookCopyServiceTest {

    @Mock
    private BookCopyRepository bookCopyRepository;

    @Mock
    private UserHistoryService userHistoryService;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookCopyService bookCopyService;

    @Test
    @DisplayName(" в методе происходит добавление новых копий для книги ")
    void addBookCopiesForBook() {
        var book = new Book();
        book.setId(1L);
        book.setBookCopies(new HashSet<>());

        when(bookRepository.findBookById(book.getId())).thenReturn(Optional.of(book));

        List<BookCopyDTO> bookCopyDTOS = new ArrayList<>();
        var bookCopyDTO = new BookCopyDTO();
        bookCopyDTO.setIsBusy(false);
        bookCopyDTOS.add(bookCopyDTO);

        bookCopyService.addBookCopiesForBook(book.getId(), bookCopyDTOS);

        var expectedBookCopy = new BookCopy();
        expectedBookCopy.setIsBusy(bookCopyDTO.getIsBusy());

        verify(bookCopyRepository, times(1)).save(expectedBookCopy);

    }
}