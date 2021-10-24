create table subscription_pricing
(
    id    bigint       not null auto_increment,
    price float        not null,
    unit  varchar(255) not null,
    primary key (id)
) engine = InnoDB