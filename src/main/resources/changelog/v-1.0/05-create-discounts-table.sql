create table discounts
(
    id              bigint            not null auto_increment,
    discount_factor float default 1.0 not null,
    expire_date     date,
    promo_code      varchar(255)      not null,
    primary key (id)
) engine = InnoDB