create table "user"
(
    id                    bigserial    not null
        constraint user_pkey
            primary key,
    birthday              date         not null,
    email                 varchar(255),
    login                 varchar(255) not null,
    name                  varchar(255) not null,
    password              varchar(255) not null,
    patronymic            varchar(255),
    readers_ticket_number bigint,
    surname               varchar(255) not null
)

GO