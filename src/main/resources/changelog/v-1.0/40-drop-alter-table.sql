alter table if exists addresses drop constraint addrss_unq;

alter table if exists addresses drop constraint str_unq;

alter table if exists cities drop constraint cty_unq;

alter table if exists countries drop constraint cntry_unq;

alter table if exists discounts drop constraint dscnt_unq;

alter table if exists password_reset_tokens drop constraint token_user_id;

alter table if exists roles drop constraint role_value;

alter table if exists scooter_models drop constraint maker_model_unq;

alter table if exists subscription_pricing drop constraint unit_unq;

alter table if exists users drop constraint email_unq;

alter table if exists users drop constraint username_unq;

alter table if exists addresses drop constraint addresses_city_id_fk;

alter table if exists cities drop constraint cities_country_id_fk;

alter table if exists clients drop constraint clients_user_id_fk;

alter table if exists maintenances drop constraint maintenances_scooter_id_fk;

alter table if exists maintenances drop constraint maintenances_user_id_fk;

alter table if exists orders drop constraint orders_scooter_id_fk;

alter table if exists orders drop constraint orders_client_id_fk;

alter table if exists password_reset_tokens drop constraint password_reset_tokens_user_id_fk;

alter table if exists rent_points drop constraint rent_points_address_id_fk;

alter table if exists scooter_model_pricing drop constraint scooter_model_pricing_scooterModel_id_fk;

alter table if exists scooters drop constraint scooters_currentPoint_id_fk;

alter table if exists scooters drop constraint scooters_model_id_fk;

alter table if exists subscriptions drop constraint subscriptions_owner_id_fk;

alter table if exists users_roles drop constraint users_roles_roles_id_fk;

alter table if exists users_roles drop constraint users_roles_users_id_fk;