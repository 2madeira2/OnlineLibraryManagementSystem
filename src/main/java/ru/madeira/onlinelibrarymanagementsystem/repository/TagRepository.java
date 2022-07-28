package ru.madeira.onlinelibrarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.madeira.onlinelibrarymanagementsystem.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String tagName);
}
