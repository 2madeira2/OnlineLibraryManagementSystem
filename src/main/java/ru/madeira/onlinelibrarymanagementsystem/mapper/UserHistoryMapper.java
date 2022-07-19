package ru.madeira.onlinelibrarymanagementsystem.mapper;

import org.mapstruct.Mapper;
import ru.madeira.onlinelibrarymanagementsystem.dto.UserHistoryDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.UserHistory;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserHistoryMapper {
    List<UserHistoryDTO> toDto(List<UserHistory> userHistories);
}
