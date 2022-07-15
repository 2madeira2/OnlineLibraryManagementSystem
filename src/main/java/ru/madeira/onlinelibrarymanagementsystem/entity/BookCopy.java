package ru.madeira.onlinelibrarymanagementsystem.entity;

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
    private Boolean isBusy;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", nullable = false)
    @ToString.Exclude
    private Book book;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "historyBook")
    @ToString.Exclude
    private Set<UserHistory> userHistorySet;

}
