create table users
(
    id       bigint       not null auto_increment,
    email    varchar(255) not null,
    password varchar(255),
    username varchar(255) not null,
    primary key (id)
) engine = InnoDB