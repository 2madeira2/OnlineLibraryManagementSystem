package ru.madeira.onlinelibrarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.madeira.onlinelibrarymanagementsystem.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    Optional<User> findUserByReadersTicketNumber(Long readerTicketNumber);

    User save(User user);
}
