create table books_and_genres
(
    book_id  bigint not null
        constraint fktqneca393px4idqccrj14p52f
            references book,
    genre_id bigint not null
        constraint fkh0wnx6d14ih1k2g3uwnbgvyry
            references genre,
    constraint books_and_genres_pkey
        primary key (book_id, genre_id)
)

GO