create table cities
(
    id         bigint       not null auto_increment,
    cityName   varchar(255) not null,
    country_id bigint,
    primary key (id)
) engine=InnoDB