create table maintenances
(
    id         bigint      not null auto_increment,
    eventEnd   datetime(6) not null,
    eventStart datetime(6) not null,
    scooter_id bigint,
    user_id    bigint,
    primary key (id)
) engine = InnoDB