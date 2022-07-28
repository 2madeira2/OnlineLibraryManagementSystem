create table genre
(
    id   bigserial    not null
        constraint genre_pkey
            primary key,
    name varchar(255) not null
)

GO