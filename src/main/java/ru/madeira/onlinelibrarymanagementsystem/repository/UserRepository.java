package ru.madeira.onlinelibrarymanagementsystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.madeira.onlinelibrarymanagementsystem.dto.UserDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAll(Pageable pageable);

    Optional<User> findUserByReadersTicketNumber(Long readerTicketNumber);

    User save(User user);

    Optional<User> findUserByLogin(String login);

    List<User> findAllByRolesName(String roleName);

    boolean existsByLoginOrReadersTicketNumber(String login, Long readerTicketNumber);

    Optional<User> findUserById(Long id);

    List<User> findAllByIdIn(List<Long> idList);

}
