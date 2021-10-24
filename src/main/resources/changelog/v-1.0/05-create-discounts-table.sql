create table discounts
(
    id             bigint            not null auto_increment,
    discountFactor float default 1.0 not null,
    expireDate     date,
    promoCode      varchar(255)      not null,
    primary key (id)
) engine = InnoDB