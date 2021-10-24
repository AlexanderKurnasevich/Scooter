create table countries
(
    id          bigint       not null auto_increment,
    countryName varchar(255) not null,
    primary key (id)
) engine = InnoDB