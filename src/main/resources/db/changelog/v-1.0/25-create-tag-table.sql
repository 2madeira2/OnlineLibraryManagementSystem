create table tag
(
    id   bigserial    not null
        constraint tag_pkey
            primary key,
    name varchar(255) not null
)

GO