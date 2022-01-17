create table scooter_models
(
    id                 bigint            not null auto_increment,
    charging_time      float             not null,
    maker              varchar(255)      not null,
    max_load           integer,
    max_range          smallint          not null,
    max_speed          smallint          not null,
    model              varchar(255)      not null,
    passenger_capacity integer default 1 not null,
    vehicle_type       varchar(255)      not null,
    primary key (id)
) engine = InnoDB