//package ru.madeira.onlinelibrarymanagementsystem.specification;
//
//import org.springframework.data.jpa.domain.Specification;
//import ru.madeira.onlinelibrarymanagementsystem.criteria.BookSearchCriteria;
//import ru.madeira.onlinelibrarymanagementsystem.entity.Author;
//import ru.madeira.onlinelibrarymanagementsystem.entity.Book;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import java.util.ArrayList;
//import java.util.List;
//
//public class BookSpecification {
//
//    public static void getSpecification(BookSearchCriteria bookSearchCriteria) {
//
//    }
//
//    @Override
//    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(authorNameContainsWord())
//        if(criteria.getAuthorOrTitle() != null) {
//            Predicate predicateForName = criteriaBuilder.like(root.get())
//            predicates.add(criteriaBuilder.like(root.get("")))
//        }
//        return null;
//    }
//
//
//
//    static Specification<Cat> hasOwnerName(final String ownerName) {
//        return (root, query, cb) -> {
//            query.distinct(true);
//            Root<Cat> cat = root;
//            Subquery<Owner> ownerSubQuery = query.subquery(Owner.class);
//            Root<Owner> owner = ownerSubQuery.from(Owner.class);
//            Expression<Collection<Cat>> ownerCats = owner.get("cats");
//            ownerSubQuery.select(owner);
//            ownerSubQuery.where(cb.equal(owner.get("name"), ownerName), cb.isMember(cat, ownerCats));
//            return cb.exists(ownerSubQuery);
//        };
//    }
//
//
//    private static Specification<Author> authorsSurnameContainsWord(String word) {
//        return (((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("surname"), "%" + word + "%")));
//    }
//
//    private static Specification<Author> authorsNameContainsWord(String word) {
//        return (((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + word + "%")));
//    }
//
//
//
//}
