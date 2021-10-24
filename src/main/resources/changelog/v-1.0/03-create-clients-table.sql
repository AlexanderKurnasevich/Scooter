create table clients
(
    id        bigint      not null auto_increment,
    firstName varchar(30) not null,
    lastName  varchar(40) not null,
    user_id   bigint      not null,
    primary key (id)
) engine = InnoDB