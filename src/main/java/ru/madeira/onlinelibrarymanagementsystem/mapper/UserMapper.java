package ru.madeira.onlinelibrarymanagementsystem.mapper;

import org.mapstruct.Mapper;
import ru.madeira.onlinelibrarymanagementsystem.dto.UserDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDTO toDto(User user);
    List<UserDTO> toDto(List<User> users);
}
