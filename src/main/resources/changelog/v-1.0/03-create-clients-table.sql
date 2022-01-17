create table clients
(
    id        bigint      not null auto_increment,
    first_name varchar(30) not null,
    last_name  varchar(40) not null,
    user_id   bigint      not null,
    primary key (id)
) engine = InnoDB