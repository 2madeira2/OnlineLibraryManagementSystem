package ru.madeira.onlinelibrarymanagementsystem.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.madeira.onlinelibrarymanagementsystem.dto.BookCopyDTO;
import ru.madeira.onlinelibrarymanagementsystem.dto.BookDTO;
import ru.madeira.onlinelibrarymanagementsystem.dto.TagDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.*;
import ru.madeira.onlinelibrarymanagementsystem.exception.BookNotFoundException;
import ru.madeira.onlinelibrarymanagementsystem.mapper.BookCopyMapper;
import ru.madeira.onlinelibrarymanagementsystem.mapper.BookMapper;
import ru.madeira.onlinelibrarymanagementsystem.mapper.TagMapper;
import ru.madeira.onlinelibrarymanagementsystem.repository.*;
import ru.madeira.onlinelibrarymanagementsystem.util.xml.XmlParser;
import ru.madeira.onlinelibrarymanagementsystem.util.xml.wrapper.Books;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final TagRepository tagRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final UserHistoryRepository userHistoryRepository;
    private final BookCopyRepository bookCopyRepository;
    private final BookMapper bookMapper;
    private final TagMapper tagMapper;
    private final BookCopyMapper bookCopyMapper;
    private final XmlMapper xmlMapper = XmlMapper.builder().addModule(new JavaTimeModule()).build();
    private final XmlParser xmlParser;


    public List<BookDTO> getAllBooks(Specification<Book> bookSpecification, Pageable pageable) {
        return bookRepository.findAll(bookSpecification, pageable)
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
        if ((genreSet = newBook.getGenres()) != null) {
            for (Genre genre : genreSet) {
                Genre genreFromDB = genreRepository.findByName(genre.getName());
                if (genreFromDB != null) {
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
        if ((authorSet = newBook.getAuthors()) != null) {
            for (Author author : authorSet) {
                Author authorFromDB = authorRepository.findBySurnameAndNameAndPatronymicAndBirthDate(author.getSurname(), author.getName(), author.getPatronymic(), author.getBirthDate());
                if (authorFromDB != null) {
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
        Book book = bookRepository.findBookById(bookId).orElseThrow(BookNotFoundException::new);
        for (TagDTO tag : tags) {
            Tag currentTag = tagRepository.findByName(tag.getName());
            if (currentTag != null) {
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
        return bookCopyMapper.toDTO(bookCopyRepository.findAllByBookId(bookId));
    }

    public void editBookInfo(Long bookId, BookDTO bookDTO) {
        if (bookRepository.existsById(bookId)) {
            Book editBook = bookMapper.toBook(bookDTO);
            editBook.setId(bookId);
            Set<Genre> genreSet;
            Set<Genre> newGenres = new HashSet<>();
            if ((genreSet = editBook.getGenres()) != null) {
                for (Genre genre : genreSet) {
                    Genre genreFromDB = genreRepository.findByName(genre.getName());
                    if (genreFromDB != null) {
                        newGenres.add(genreFromDB);
                    } else {
                        genreRepository.save(genre);
                        newGenres.add(genre);
                    }
                }
            }
            editBook.setGenres(newGenres);
            Set<Author> authorSet;
            Set<Author> newAuthors = new HashSet<>();
            if ((authorSet = editBook.getAuthors()) != null) {
                for (Author author : authorSet) {
                    Author authorFromDB = authorRepository.findBySurnameAndNameAndPatronymicAndBirthDate(author.getSurname(), author.getName(), author.getPatronymic(), author.getBirthDate());
                    if (authorFromDB != null) {
                        newAuthors.add(authorFromDB);
                    } else {
                        authorRepository.save(author);
                        newAuthors.add(author);
                    }
                }
            }
            editBook.setAuthors(newAuthors);
            bookRepository.save(editBook);
        } else {
            throw new BookNotFoundException();
        }
    }

    public void addBooks(List<Book> books) {
        for (Book book : books) {
            advancedCreateBook(bookMapper.toDto(book));
        }
    }

    public BookDTO getBookById(Long bookId) {
        return bookMapper.toDto(bookRepository.findBookById(bookId).orElseThrow(BookNotFoundException::new));
    }

    public void parseXmlAndAddBooks(String bookXml) throws JAXBException {
        xmlMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        Books books = (Books) xmlParser.getParseObjectFromXml(bookXml, Books.class, Author.class);
        addBooks(books.getBooks());
    }
}
