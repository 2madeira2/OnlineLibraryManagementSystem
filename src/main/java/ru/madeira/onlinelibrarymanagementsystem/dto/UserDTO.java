package ru.madeira.onlinelibrarymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import ru.madeira.onlinelibrarymanagementsystem.entity.Role;
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
    private Set<RoleDTO> roles;
    private String email;
    private String login;
    private Long readersTicketNumber;

    public UserDTO(String surname, String name, String patronymic, LocalDate birthday, Set<RoleDTO> roles, String email, String login, Long readersTicketNumber) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.roles = roles;
        this.email = email;
        this.login = login;
        this.readersTicketNumber = readersTicketNumber;
    }
}
