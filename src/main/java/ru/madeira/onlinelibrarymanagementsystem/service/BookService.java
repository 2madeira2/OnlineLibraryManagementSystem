package ru.madeira.onlinelibrarymanagementsystem.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.madeira.onlinelibrarymanagementsystem.dto.BookCopyDTO;
import ru.madeira.onlinelibrarymanagementsystem.dto.BookDTO;
import ru.madeira.onlinelibrarymanagementsystem.dto.TagDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.*;
import ru.madeira.onlinelibrarymanagementsystem.mapper.BookMapper;
import ru.madeira.onlinelibrarymanagementsystem.mapper.TagMapper;
import ru.madeira.onlinelibrarymanagementsystem.repository.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final TagRepository tagRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final UserHistoryRepository userHistoryRepository;
    private final BookCopyRepository bookCopyRepository;
    private final BookMapper bookMapper;
    private final TagMapper tagMapper;

    public BookService(BookRepository bookRepository, TagRepository tagRepository, GenreRepository genreRepository, AuthorRepository authorRepository, UserHistoryRepository userHistoryRepository, BookCopyRepository bookCopyRepository, BookMapper bookMapper, TagMapper tagMapper) {
        this.bookRepository = bookRepository;
        this.tagRepository = tagRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.userHistoryRepository = userHistoryRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.bookMapper = bookMapper;
        this.tagMapper = tagMapper;
    }

    public List<BookDTO> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .stream().map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDTO createBook(BookDTO book) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toBook(book)));
    }

    public BookDTO advancedCreateBook(BookDTO bookDTO) {
        Book newBook = bookMapper.toBook(bookDTO);
        Set<Genre> genreSet;
        Set<Genre> newGenres = new HashSet<>();
        if((genreSet = newBook.getGenres()) != null) {
            for(Genre genre : genreSet) {
                Genre genreFromDB = genreRepository.findByName(genre.getName());
                if(genreFromDB != null) {
                    newGenres.add(genreFromDB);
                } else {
                    genreRepository.save(genre);
                    newGenres.add(genre);
                }
            }
        }
        newBook.setGenres(newGenres);
        Set<Author> authorSet;
        Set<Author> newAuthors = new HashSet<>();
        if((authorSet = newBook.getAuthors()) != null) {
            for(Author author : authorSet) {
                Author authorFromDB = authorRepository.findBySurnameAndNameAndPatronymicAndBirthDate(author.getSurname(), author.getName(), author.getPatronymic(), author.getBirthDate());
                if(authorFromDB != null) {
                    newAuthors.add(authorFromDB);
                } else {
                    authorRepository.save(author);
                    newAuthors.add(author);
                }
            }
        }
        newBook.setAuthors(newAuthors);
        return bookMapper.toDto(bookRepository.save(newBook));
    }

    @Transactional
    public List<BookDTO> findBooksByAuthor(String authorName) {
        List<Book> books = new ArrayList<>(bookRepository.findBooksByAuthorsNameContains(authorName));
        books.addAll(bookRepository.findBooksByAuthorsSurnameContains(authorName));
        books.addAll(bookRepository.findBooksByAuthorsPatronymicContains(authorName));
        return bookMapper.toDto(books);
    }

    @Transactional
    public void addTagsToBook(Long bookId, List<TagDTO> tags) {
        Book book = bookRepository.findBookById(bookId);
        for(TagDTO tag : tags) {
            Tag currentTag = tagRepository.findByName(tag.getName());
            if(currentTag != null) {
                book.getTags().add(currentTag);
            } else {
                Tag newTag = tagMapper.toTag(tag);
                tagRepository.save(newTag);
                book.getTags().add(newTag);
            }
        }
        bookRepository.save(book);
    }

    public List<BookCopyDTO> getAllCopiesByBookId(Long bookId) {
        return bookCopyRepository.findAllByBookId(bookId);
    }

    public void editBookInfo(Long bookId, BookDTO bookDTO) {
        Book book = bookRepository.findBookById(bookId);
        Book newBook = bookMapper.toBook(bookDTO);
        book.setTitle(bookDTO.getTitle());
        book.setDescription(bookDTO.getDescription());
        book.setYear(bookDTO.getYear());
        Set<Genre> genreSet;
        Set<Genre> newGenres = new HashSet<>();
        if((genreSet = newBook.getGenres()) != null) {
            for(Genre genre : genreSet) {
                Genre genreFromDB = genreRepository.findByName(genre.getName());
                if(genreFromDB != null) {
                    newGenres.add(genreFromDB);
                } else {
                    genreRepository.save(genre);
                    newGenres.add(genre);
                }
            }
        }
        book.setGenres(newGenres);
        Set<Author> authorSet;
        Set<Author> newAuthors = new HashSet<>();
        if((authorSet = newBook.getAuthors()) != null) {
            for(Author author : authorSet) {
                Author authorFromDB = authorRepository.findBySurnameAndNameAndPatronymicAndBirthDate(author.getSurname(), author.getName(), author.getPatronymic(), author.getBirthDate());
                if(authorFromDB != null) {
                    newAuthors.add(authorFromDB);
                } else {
                    authorRepository.save(author);
                    newAuthors.add(author);
                }
            }
        }
        book.setAuthors(newAuthors);
        bookRepository.save(book);
    }
}
