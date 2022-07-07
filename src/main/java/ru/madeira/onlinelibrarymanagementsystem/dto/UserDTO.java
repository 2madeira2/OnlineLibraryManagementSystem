package ru.madeira.onlinelibrarymanagementsystem.dto;

import lombok.Data;
import ru.madeira.onlinelibrarymanagementsystem.entity.UserHistory;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class UserDTO {

    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate birthday;
    private List<String> roles;
    private String email;
    private String login;
    private Long readersTicketNumber;
    private Set<UserHistory> userHistorySet;

    public UserDTO(Long id, String surname, String name, String patronymic, LocalDate birthday, List<String> roles, String email, String login, Long readersTicketNumber, Set<UserHistory> userHistorySet) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.roles = roles;
        this.email = email;
        this.login = login;
        this.readersTicketNumber = readersTicketNumber;
        this.userHistorySet = userHistorySet;
    }
}
