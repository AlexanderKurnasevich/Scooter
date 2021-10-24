create table password_reset_tokens
(
    id         bigint       not null auto_increment,
    expiryDate datetime(6)  not null,
    token      varchar(255) not null,
    user_id    bigint       not null,
    primary key (id)
) engine = InnoDB