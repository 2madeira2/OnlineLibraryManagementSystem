package ru.madeira.onlinelibrarymanagementsystem.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.madeira.onlinelibrarymanagementsystem.criteria.BookSearchCriteria;
import ru.madeira.onlinelibrarymanagementsystem.entity.Author;
import ru.madeira.onlinelibrarymanagementsystem.entity.Book;
import ru.madeira.onlinelibrarymanagementsystem.entity.Genre;

import javax.persistence.criteria.*;

public class BookSpecification {

    public static Specification<Book> getBookSpecification(BookSearchCriteria bookSearchCriteria) {
        Specification<Book> filter = Specification.where(null);
        if(bookSearchCriteria.getWord() != null) {
            Specification<Book> bookSpecification = Specification.where(titleOrAuthorContainsWord(bookSearchCriteria.getWord()));
            bookSpecification = bookSpecification.or(authorSurnameContainsWord(bookSearchCriteria.getWord()));
            filter = filter.and(bookSpecification);
            //filter = filter.and(/*titleOrAuthorContainsWord(bookSearchCriteria.getWord()).or(*/authorSurnameContainsWord(bookSearchCriteria.getWord()));
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
           Join<Book, Author> bookAuthorJoin = root.join("authors");
           return criteriaBuilder.like(bookAuthorJoin.get("surname"), "%" + word + "%");
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


//    @Override
//    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//        Predicate predicate = criteriaBuilder.conjunction();
//        List<Predicate> predicates = new ArrayList<>();
//        if(bookSearchCriteria != null) {
//            if(bookSearchCriteria.getGenre() != null) {
//                Join<Book, Genre> bookGenreJoin = root.join("genres");
//                predicates.add(criteriaBuilder.like(bookGenreJoin.get("name"), bookSearchCriteria.getGenre()));
//            }
//            if(bookSearchCriteria.getMaxYear() != null) {
//                predicates.add(criteriaBuilder.le(root.get("year"), bookSearchCriteria.getMaxYear()));
//            }
//            if(bookSearchCriteria.getMinYear() != null) {
//                predicates.add(criteriaBuilder.ge(root.get("year"), bookSearchCriteria.getMinYear()));
//            }
//            if(bookSearchCriteria.getWord() != null) {
//                //Join<Book, Author> bookAuthorJoin = root.join("authors");
//                // Predicate surnamePredicate = criteriaBuilder.like(bookAuthorJoin.get("surname"), "%" + bookSearchCriteria.getAuthorOrTitle() + "%");
//                //Predicate titlePredicate = criteriaBuilder.like(root.get("title"), "%" + bookSearchCriteria.getAuthorOrTitle() + "%");
//                // Predicate authorOrTitlePredicate = criteriaBuilder.or(surnamePredicate, titlePredicate);
//                predicates.add(criteriaBuilder.like(root.get("title"), "%" + bookSearchCriteria.getWord() + "%"));
//            }
//        }
//        for(Predicate predicate1 : predicates) {
//            predicate = criteriaBuilder.and(predicate1);
//        }
//        return predicate;
//    }
}
