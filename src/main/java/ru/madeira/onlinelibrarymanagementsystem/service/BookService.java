package ru.madeira.onlinelibrarymanagementsystem.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.madeira.onlinelibrarymanagementsystem.dto.BookDTO;
import ru.madeira.onlinelibrarymanagementsystem.dto.TagDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.Book;
import ru.madeira.onlinelibrarymanagementsystem.entity.Tag;
import ru.madeira.onlinelibrarymanagementsystem.mapper.BookMapper;
import ru.madeira.onlinelibrarymanagementsystem.mapper.TagMapper;
import ru.madeira.onlinelibrarymanagementsystem.repository.BookRepository;
import ru.madeira.onlinelibrarymanagementsystem.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final TagRepository tagRepository;
    private final BookMapper bookMapper;
    private final TagMapper tagMapper;

    public BookService(BookRepository bookRepository, TagRepository tagRepository, BookMapper bookMapper, TagMapper tagMapper) {
        this.bookRepository = bookRepository;
        this.tagRepository = tagRepository;
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

    public List<BookDTO> findBooksByAuthor(String authorName) {
        List<Book> books = new ArrayList<>(bookRepository.findBooksByAuthorsNameContains(authorName));
        books.addAll(bookRepository.findBooksByAuthorsSurnameContains(authorName));
        books.addAll(bookRepository.findBooksByAuthorsPatronymicContains(authorName));
        return bookMapper.toDto(books);
    }

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
}
