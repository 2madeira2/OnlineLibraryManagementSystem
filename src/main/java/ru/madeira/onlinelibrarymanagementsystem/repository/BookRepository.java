package ru.madeira.onlinelibrarymanagementsystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.madeira.onlinelibrarymanagementsystem.dto.BookCopyDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.Book;
import ru.madeira.onlinelibrarymanagementsystem.entity.BookCopy;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBookByTitle(String title);
    Page<Book> findAll(Pageable pageable);
    Book findBookById(Long id);
    List<Book> findBooksByAuthorsSurnameContains(String authorsName);
    List<Book> findBooksByAuthorsNameContains(String authorsName);
    List<Book> findBooksByAuthorsPatronymicContains(String patronymic);
    Boolean existsByTitleAndAuthorsSurnameAndAuthorsNameAndAuthorsPatronymic(String title, String authorsSurname, String authorsName, String authorsPatronymic);
}
