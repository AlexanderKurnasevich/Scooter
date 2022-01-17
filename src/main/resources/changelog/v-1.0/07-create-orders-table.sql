create table orders
(
    id         bigint            not null auto_increment,
    event_end   datetime(6)       not null,
    event_start datetime(6)       not null,
    mileage    integer           not null,
    price      float default 0.0 not null,
    scooter_id bigint,
    client_id  bigint,
    primary key (id)
) engine = InnoDB