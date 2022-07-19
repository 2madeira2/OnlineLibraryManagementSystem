package ru.madeira.onlinelibrarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.madeira.onlinelibrarymanagementsystem.entity.Genre;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Boolean existsGenreByName(String genreName);
}
