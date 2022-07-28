create table user_history
(
    id              bigserial not null
        constraint user_history_pkey
            primary key,
    receipt_date    date      not null,
    release_date    date      not null,
    book_copy_id    bigint    not null
        constraint fkjq8h6oj87m8b1rn48xaenowkh
            references book_copy,
    user_id         bigint    not null
        constraint fk6jf5oqicy8v01lvtrmmwvthn6
            references "user",
    return_date     date
)

GO