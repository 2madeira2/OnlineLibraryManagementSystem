create table users_and_roles
(
    user_id bigint not null
        constraint fk8jjh1jouavafs68px4ue2gev3
            references "user",
    role_id bigint not null
        constraint fk3slf125j5r1npaimtqaywvk0a
            references role,
    constraint users_and_roles_pkey
        primary key (user_id, role_id)
)

GO