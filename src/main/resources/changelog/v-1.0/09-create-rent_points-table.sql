create table rent_points
(
    id         bigint not null auto_increment,
    address_id bigint,
    primary key (id)
) engine = InnoDB