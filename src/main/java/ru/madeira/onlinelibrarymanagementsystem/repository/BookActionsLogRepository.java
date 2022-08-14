package ru.madeira.onlinelibrarymanagementsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.madeira.onlinelibrarymanagementsystem.entity.BookActionsLog;

@Repository
public interface BookActionsLogRepository extends MongoRepository<BookActionsLog, String> {
}
