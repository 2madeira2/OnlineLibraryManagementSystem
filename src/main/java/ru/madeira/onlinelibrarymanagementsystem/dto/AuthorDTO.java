package ru.madeira.onlinelibrarymanagementsystem.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorDTO {
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate birthDate;

    private LocalDate deathDate;
}
