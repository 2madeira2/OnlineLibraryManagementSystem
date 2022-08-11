package ru.madeira.onlinelibrarymanagementsystem.criteria;

import lombok.Data;

@Data
public class BookSearchCriteria {
    private String authorOrTitle;
    private String genre;
    private Long minYear;
    private Long maxYear;
}
