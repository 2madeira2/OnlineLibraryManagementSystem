package ru.madeira.onlinelibrarymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.madeira.onlinelibrarymanagementsystem.dto.UserHistoryDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.BookCopy;
import ru.madeira.onlinelibrarymanagementsystem.entity.User;
import ru.madeira.onlinelibrarymanagementsystem.entity.UserHistory;
import ru.madeira.onlinelibrarymanagementsystem.exception.DebtsExistenceException;
import ru.madeira.onlinelibrarymanagementsystem.exception.FreeBookCopiesNotFoundException;
import ru.madeira.onlinelibrarymanagementsystem.exception.UserNotFoundException;
import ru.madeira.onlinelibrarymanagementsystem.mapper.UserHistoryMapper;
import ru.madeira.onlinelibrarymanagementsystem.repository.BookCopyRepository;
import ru.madeira.onlinelibrarymanagementsystem.repository.UserHistoryRepository;
import ru.madeira.onlinelibrarymanagementsystem.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserHistoryService {

    private UserHistoryRepository userHistoryRepository;
    private UserRepository userRepository;
    private BookCopyRepository bookCopyRepository;
    private UserHistoryMapper userHistoryMapper;
    private static final Integer NUMBER_OF_WEEKS_FOR_BOOK_TO_BE_READ_BY_USER = 2;

    @Autowired
    public void setUserHistoryRepository(UserHistoryRepository userHistoryRepository, UserRepository userRepository, BookCopyRepository bookCopyRepository, UserHistoryMapper userHistoryMapper) {
        this.userHistoryRepository = userHistoryRepository;
        this.userRepository = userRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.userHistoryMapper = userHistoryMapper;
    }

    public List<UserHistoryDTO> getUserHistoryByUserId(Long id) {
        return userHistoryMapper.toDto(userHistoryRepository.findAllByUserId(id));
    }

    public List<UserHistoryDTO> getUserAppearsByUserId(Long id) {
        return userHistoryMapper.toDto(userHistoryRepository.findAllByUserIdAndReturnDateIsNull(id));
    }

    public void releaseUsersBook(Long id, Long bookCopyId) {
        UserHistory userHistory = userHistoryRepository.findUserHistoryByUserIdAndHistoryBookId(id, bookCopyId);
        userHistory.setReturnDate(LocalDate.now());
        userHistoryRepository.save(userHistory);
    }

    public Long createNewUserHistoryRecord(Long bookId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findUserByLogin(userDetails.getUsername()).orElseThrow(UserNotFoundException::new);
        if (userHistoryRepository.existsByUserIdAndReturnDateIsNull(currentUser.getId())) {
            throw new DebtsExistenceException();
        }
        BookCopy bookCopy = bookCopyRepository.findBookCopyByBookIdAndIsBusyFalse(bookId).orElseThrow(FreeBookCopiesNotFoundException::new);
        Long bookCopyId = bookCopy.getId();
        UserHistory userHistory = new UserHistory();
        userHistory.setUser(currentUser);
        userHistory.setHistoryBook(bookCopy);
        LocalDate receiptDate = LocalDate.now();
        userHistory.setReceiptDate(receiptDate);
        userHistory.setReleaseDate(receiptDate.plusWeeks(NUMBER_OF_WEEKS_FOR_BOOK_TO_BE_READ_BY_USER));
        userHistoryRepository.save(userHistory);
        return bookCopyId;
    }

    public List<Long> findAllUsersIdWhoHaveDebts() {
        return userHistoryRepository.findAllByReturnDateIsNull()
                .stream()
                .filter(u -> u.getReleaseDate().isBefore(LocalDate.now()))
                .map(UserHistory::getId)
                .collect(Collectors.toList());
    }

    public List<UserHistoryDTO> getUsersWhoTookBookCopy(Long bookCopyId) {
        return userHistoryMapper.toDto(userHistoryRepository.findAllByUserId(bookCopyId));
    }
}
