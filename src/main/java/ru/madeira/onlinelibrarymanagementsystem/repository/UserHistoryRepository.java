package ru.madeira.onlinelibrarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.madeira.onlinelibrarymanagementsystem.entity.UserHistory;

import java.util.List;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
    List<UserHistory> findAllByUserId(Long id);
    List<UserHistory> findAllByUserIdAndReturnDateIsNull(Long id);
    UserHistory findUserHistoryByUserIdAndHistoryBookId(Long userId, Long bookCopyId);
    Boolean existsByUserIdAndReturnDateIsNull(Long userId);
}
