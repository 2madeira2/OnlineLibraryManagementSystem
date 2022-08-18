package ru.madeira.onlinelibrarymanagementsystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.madeira.onlinelibrarymanagementsystem.dto.BookCopyDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.Book;
import ru.madeira.onlinelibrarymanagementsystem.entity.BookCopy;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    List<Book> findBookByTitle(String title);
    Page<Book> findAll(Specification<Book> bookSpecification, Pageable pageable);
    Optional<Book> findBookById(Long id);
    List<Book> findBooksByAuthorsSurnameContains(String authorsName);
    List<Book> findBooksByAuthorsNameContains(String authorsName);
    List<Book> findBooksByAuthorsPatronymicContains(String patronymic);
    Boolean existsByTitleAndAuthorsSurnameAndAuthorsNameAndAuthorsPatronymic(String title, String authorsSurname, String authorsName, String authorsPatronymic);
}
