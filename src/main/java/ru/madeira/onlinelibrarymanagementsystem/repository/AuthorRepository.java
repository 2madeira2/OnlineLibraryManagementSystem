package ru.madeira.onlinelibrarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.madeira.onlinelibrarymanagementsystem.entity.Author;
import ru.madeira.onlinelibrarymanagementsystem.entity.Book;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findBySurnameAndNameAndPatronymicAndBirthDate(String surname, String name, String patronymic, LocalDate birthdate);
}
