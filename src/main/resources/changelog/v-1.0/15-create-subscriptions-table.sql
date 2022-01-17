create table subscriptions
(
    id         bigint       not null auto_increment,
    expiry_day date         not null,
    price      float        not null,
    quantity   integer      not null,
    unit       varchar(255) not null,
    owner_id   bigint,
    primary key (id)
) engine = InnoDB