package ru.madeira.onlinelibrarymanagementsystem.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.madeira.onlinelibrarymanagementsystem.criteria.BookSearchCriteria;
import ru.madeira.onlinelibrarymanagementsystem.entity.Author;
import ru.madeira.onlinelibrarymanagementsystem.entity.Book;
import ru.madeira.onlinelibrarymanagementsystem.entity.Genre;

import javax.persistence.criteria.*;
import java.util.Collection;

public class BookSpecification {

    public static Specification<Book> getBookSpecification(BookSearchCriteria bookSearchCriteria) {
        Specification<Book> filter = Specification.where(null);
        if(bookSearchCriteria.getWord() != null) {
            filter = filter.and(titleOrAuthorContainsWord(bookSearchCriteria.getWord()).or(authorSurnameContainsWord(bookSearchCriteria.getWord())));
        }
        if(bookSearchCriteria.getMaxYear() != null) {
            filter = filter.and(yearLessThan(bookSearchCriteria.getMaxYear()));
        }
        if(bookSearchCriteria.getMinYear() != null) {
            filter = filter.and(yearGreaterThan(bookSearchCriteria.getMinYear()));
        }
        if(bookSearchCriteria.getGenre() != null) {
            filter = filter.and(genreIs(bookSearchCriteria.getGenre()));
        }
        return filter;
    }


    public static Specification<Book> titleOrAuthorContainsWord(String word) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + word + "%");
    }

    public static Specification<Book> authorSurnameContainsWord(String word) {
        return (root, query, criteriaBuilder) ->
        {
            query.distinct(true);
            Root<Author> author = query.from(Author.class);
            Expression<Collection<Book>> authorsBooks = author.get("books");
            return criteriaBuilder.and(criteriaBuilder.like(author.get("surname"), word), criteriaBuilder.isMember(root, authorsBooks));
        };
    }

    public static Specification<Book> genreIs(String genreName) {
        return (root, query, criteriaBuilder) ->
        {
            Join<Book, Genre> bookGenreJoin = root.join("genres");
            return criteriaBuilder.like(bookGenreJoin.get("name"), genreName);
        };
    }

    public static Specification<Book> yearGreaterThan(Long minYear) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("year"), minYear));
    }

    public static Specification<Book> yearLessThan(Long maxYear) {
        return (((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("year"), maxYear)));
    }
}
