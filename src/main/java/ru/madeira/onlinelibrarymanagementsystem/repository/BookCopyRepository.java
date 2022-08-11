package ru.madeira.onlinelibrarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.madeira.onlinelibrarymanagementsystem.dto.BookCopyDTO;
import ru.madeira.onlinelibrarymanagementsystem.dto.UserHistoryDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.BookCopy;
import ru.madeira.onlinelibrarymanagementsystem.entity.UserHistory;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {

    Optional<Long> getBookCopyIdByBookIdAndIsBusyIsFalse(Long bookId);

    Optional<BookCopy> findBookCopyByBookIdAndIsBusyFalse(Long bookId);

    @Modifying
    @Query("update BookCopy bc set bc.isBusy = true where bc.id = :bookCopyId")
    void updateBookCopyStatus(Long bookCopyId);

    Optional<BookCopy> getBookCopyById(Long id);

    List<BookCopyDTO> findAllByBookId(Long bookId);
}
