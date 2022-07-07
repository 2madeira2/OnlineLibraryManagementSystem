package ru.madeira.onlinelibrarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.madeira.onlinelibrarymanagementsystem.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBookByTitle(String title);
    List<Book> findBookByAuthorsSurname(String authorsSurname);
    List<Book> findAll();
}
