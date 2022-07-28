create table books_and_tags
(
    book_id bigint not null
        constraint fk8v38xud72a254jeo94d1umne5
            references book,
    tag_id  bigint not null
        constraint fkt97o4hktown1xkn4ywasgs0s8
            references tag,
    constraint books_and_tags_pkey
        primary key (book_id, tag_id)
)

GO