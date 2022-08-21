package ru.madeira.onlinelibrarymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;

    @Column(nullable = false, length = 100)
    @XmlElement
    private String title;

    @Column(nullable = false)
    @XmlElement
    private Integer year;

    @Column(length = 5000)
    private String description;

    @ManyToMany
//    @Cascade({
//            CascadeType.SAVE_UPDATE,
//            CascadeType.MERGE,
//            CascadeType.PERSIST
//    })
    @JsonBackReference(value = "authors")
    @JoinTable(
            name = "authors_and_books",
            joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    @XmlElement(name = "author")
    @XmlElementWrapper(name="authors")
    private Set<Author> authors;

    @ManyToMany
    @JsonBackReference(value = "genres")
    @JoinTable(
            name = "books_and_genres",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    @XmlElement(name = "genre")
    @XmlElementWrapper(name = "genres")
    private Set<Genre> genres;

    @ManyToMany
    @JsonBackReference(value = "tags")
    @JoinTable(
            name = "books_and_tags",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private Set<Tag> tags;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    @ToString.Exclude
    @JsonBackReference(value = "copies")
    private Set<BookCopy> bookCopies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return id != null && Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
