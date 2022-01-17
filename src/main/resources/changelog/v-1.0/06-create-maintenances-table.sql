create table maintenances
(
    id         bigint      not null auto_increment,
    event_end   datetime(6) not null,
    event_start datetime(6) not null,
    scooter_id bigint,
    user_id    bigint,
    primary key (id)
) engine = InnoDB