create table book_copy
(
    id      bigserial not null
        constraint book_copy_pkey
            primary key,
    book_id bigint    not null
        constraint fkpqftp65hd66ae8wsx7pp2cxcs
            references book,
    is_busy boolean
)

GO