package ru.madeira.onlinelibrarymanagementsystem.mapper;

import org.springframework.stereotype.Component;
import ru.madeira.onlinelibrarymanagementsystem.dto.UserDTO;
import ru.madeira.onlinelibrarymanagementsystem.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyUserMapper {
    public List<UserDTO> toDto(List<User> users) {
        return users.stream()
                .map(u -> new UserDTO(u.getId(), u.getSurname(), u.getName(), u.getPatronymic(), u.getBirthday(), u.getRole(), u.getEmail(), u.getLogin(), u.getReadersTicketNumber(), u.getUserHistorySet()))
                .collect(Collectors.toList());
    }
}
