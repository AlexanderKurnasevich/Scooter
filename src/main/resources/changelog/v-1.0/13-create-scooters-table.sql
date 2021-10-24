create table scooters
(
    id              bigint            not null auto_increment,
    odometer        integer default 0 not null,
    currentPoint_id bigint,
    model_id        bigint            not null,
    primary key (id)
) engine = InnoDB