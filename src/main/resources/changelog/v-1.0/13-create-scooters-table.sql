create table scooters
(
    id               bigint            not null auto_increment,
    odometer         integer default 0 not null,
    current_point_id bigint,
    model_id         bigint            not null,
    primary key (id)
) engine = InnoDB