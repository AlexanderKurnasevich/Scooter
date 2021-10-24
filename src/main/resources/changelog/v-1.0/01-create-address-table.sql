create table addresses
(
    id      bigint       not null auto_increment,
    number  smallint,
    postfix varchar(255),
    street  varchar(255) not null,
    city_id bigint,
    primary key (id)
) engine = InnoDB