create table users_roles
(
    users_id bigint not null,
    roles_id bigint not null,
    primary key (users_id, roles_id)
) engine = InnoDB