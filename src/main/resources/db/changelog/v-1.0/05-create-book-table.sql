create table book
(
    id          bigserial     not null
        constraint book_pkey
            primary key,
    description varchar(5000) not null,
    title       varchar(100)  not null,
    year        integer       not null
)

GO

