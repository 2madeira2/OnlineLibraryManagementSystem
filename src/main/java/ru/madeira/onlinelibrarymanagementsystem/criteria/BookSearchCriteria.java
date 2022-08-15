package ru.madeira.onlinelibrarymanagementsystem.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookSearchCriteria {
    private String word;
    private String genre;
    private Long minYear;
    private Long maxYear;
}
