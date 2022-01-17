create table scooter_model_pricing
(
    id               bigint not null auto_increment,
    day_price        float  not null,
    hour_price       float  not null,
    minute_price     float  not null,
    scooter_model_id bigint not null,
    primary key (id)
) engine = InnoDB