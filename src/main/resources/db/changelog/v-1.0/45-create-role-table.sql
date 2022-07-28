create table role
(
    id   bigserial    not null
        constraint role_pkey
            primary key,
    name varchar(255) not null
        constraint uk_8sewwnpamngi6b1dwaa88askk
            unique
)

GO