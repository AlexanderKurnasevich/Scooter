create table scooter_models
(
    id                bigint            not null auto_increment,
    chargingTime      float             not null,
    maker             varchar(255)      not null,
    maxLoad           integer,
    maxRange          smallint          not null,
    maxSpeed          smallint          not null,
    model             varchar(255)      not null,
    passengerCapacity integer default 1 not null,
    vehicleType       varchar(255)      not null,
    primary key (id)
) engine = InnoDB