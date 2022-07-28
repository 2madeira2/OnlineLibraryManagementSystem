create table author
(
    id         bigserial    not null
        constraint author_pkey
            primary key,
    birth_date date         not null,
    death_date date,
    name       varchar(255) not null,
    patronymic varchar(255),
    surname    varchar(255) not null
)

GO