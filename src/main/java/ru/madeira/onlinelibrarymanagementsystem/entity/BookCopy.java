package ru.madeira.onlinelibrarymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book_copy")
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean isBusy;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", nullable = false)
    @ToString.Exclude
    @JsonBackReference
    private Book book;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "historyBook")
    @ToString.Exclude
    @JsonBackReference
    private Set<UserHistory> userHistorySet;

}
