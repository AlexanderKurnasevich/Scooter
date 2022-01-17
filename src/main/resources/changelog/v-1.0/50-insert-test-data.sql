insert into roles (value) value ('ROLE_ADMIN');

insert into roles (value) value ('ROLE_CLIENT');

insert into countries (id, country_name)
values ('1', 'Belarus');

insert into cities (id, city_name, country_id)
values ('1', 'Brest', '1');
insert into cities (id, city_name, country_id)
values ('2', 'Vitebsk', '1');
insert into cities (id, city_name, country_id)
values ('3', 'Gomel', '1');
insert into cities (id, city_name, country_id)
values ('4', 'Grodno', '1');
insert into cities (id, city_name, country_id)
values ('5', 'Mogilev', '1');
insert into cities (id, city_name, country_id)
values ('6', 'Minsk', '1');

insert into addresses (id, number, street, city_id)
values ('1', '2', 'pl. Sovetskaya', '4');

# insert into users (email, password, username)
# VALUES ('admin@scooter.by', '$2a$10$EUyj.zyrqfam2RfLaTDx3.SpguKcCqmS63tPqB9wudglOR0WL6GAm', 'admin');
# insert into users_roles (users_id, roles_id) VALUES (1, 1);

# insert into users (email, password, username)
# VALUES ('17alex17@tut.by', '$2a$10$eFT/tEfymFUt1HPOC5iLEuSh7asr80EgBIbhoPQ5pj9vMpOhUiHmG', 'test');
# insert into clients (id, firstName, lastName, user_id)
# VALUES ('1', 'name', 'fam', '2');
# insert into users_roles (users_id, roles_id) VALUES (2, 2);

insert into discounts (id, discount_factor, expire_date, promo_code)
VALUES ('1', '0.9', '2222-12-12', 'PROMO');

insert into rent_points (address_id) value ('1');

insert into scooter_models (charging_time, maker, max_load, max_range, max_speed, model, passenger_capacity,
                            vehicle_type)
values ('5', 'Xiaomi', '100', '30', '25', 'Mi Electric Scooter 1S M365S', '1', 'SCOOTER');

insert into scooter_models (charging_time, maker, max_load, max_range, max_speed, model, passenger_capacity,
                            vehicle_type)
values ('5', 'Test', '100', '30', '25', 'Test', '10', 'HEAVY_SCOOTER');

insert into scooter_model_pricing (day_price, hour_price, minute_price, scooter_model_id)
values ('1000', '100', '10', '1');

insert into scooter_model_pricing (day_price, hour_price, minute_price, scooter_model_id)
values ('1111', '111', '11', '2');

insert into scooters (odometer, current_point_id, model_id) values ('110', '1', '1');
insert into scooters (odometer, current_point_id, model_id) values ('0', '1', '1');
insert into scooters (odometer, current_point_id, model_id) values ('0', '1', '2');

insert into subscription_pricing (price, unit) values ('1000', 'DAYS');







