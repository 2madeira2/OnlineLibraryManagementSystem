package ru.madeira.onlinelibrarymanagementsystem.service;

import org.springframework.stereotype.Service;
import ru.madeira.onlinelibrarymanagementsystem.entity.BookActionsLog;
import ru.madeira.onlinelibrarymanagementsystem.repository.BookActionsLogRepository;

@Service
public class BookActionsLogService {

    private final BookActionsLogRepository bookActionsLogRepository;

    public BookActionsLogService(BookActionsLogRepository bookActionsLogRepository) {
        this.bookActionsLogRepository = bookActionsLogRepository;
    }

    public void saveLog(BookActionsLog bookActionsLog) {
        this.bookActionsLogRepository.insert(bookActionsLog);
    }

}
