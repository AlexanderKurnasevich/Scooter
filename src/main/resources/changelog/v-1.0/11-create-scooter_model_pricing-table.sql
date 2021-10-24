create table scooter_model_pricing
(
    id              bigint not null auto_increment,
    dayPrice        float  not null,
    hourPrice       float  not null,
    minutePrice     float  not null,
    scooterModel_id bigint not null,
    primary key (id)
) engine = InnoDB