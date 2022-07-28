create table authors_and_books
(
    author_id bigint not null
        constraint fk99qsvruh6f9fqmphvsy7fg7b2
            references book,
    book_id   bigint not null
        constraint fkc6hrxcpjnit6pwnmhg7u729ia
            references author,
    constraint authors_and_books_pkey
        primary key (author_id, book_id)
)

GO