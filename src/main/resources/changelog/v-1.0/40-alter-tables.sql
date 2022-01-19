alter table addresses
    add constraint addrss_unq unique (street, number, postfix, city_id);

# alter table addresses
#     add constraint str_unq unique (street) ;

alter table cities
    add constraint cty_unq unique (city_name, country_id) ;

alter table countries
    add constraint cntry_unq unique (country_name) ;

alter table discounts
    add constraint dscnt_unq unique (promo_code) ;

alter table password_reset_tokens
    add constraint token_user_id unique (user_id) ;

alter table roles
    add constraint role_value unique (value) ;

alter table scooter_models
    add constraint maker_model_unq unique (maker, model) ;

alter table subscription_pricing
    add constraint unit_unq unique (unit) ;

alter table users
    add constraint email_unq unique (email) ;

alter table users
    add constraint username_unq unique (username) ;

alter table addresses
    add constraint addresses_city_id_fk
        foreign key (city_id)
            references cities (id) ;

alter table cities
    add constraint cities_country_id_fk
        foreign key (country_id)
            references countries (id) ;

alter table clients
    add constraint clients_user_id_fk
        foreign key (user_id)
            references users (id) ;

# alter table maintenances
#     add constraint maintenances_scooter_id_fk
#         foreign key (scooter_id)
#             references scooters (id) ;
#
# alter table maintenances
#     add constraint maintenances_user_id_fk
#         foreign key (user_id)
#             references users (id) ;

alter table orders
    add constraint orders_scooter_id_fk
        foreign key (scooter_id)
            references scooters (id) ;

alter table orders
    add constraint orders_client_id_fk
        foreign key (client_id)
            references clients (id) ;

alter table password_reset_tokens
    add constraint password_reset_tokens_user_id_fk
        foreign key (user_id)
            references users (id) ;

alter table rent_points
    add constraint rent_points_address_id_fk
        foreign key (address_id)
            references addresses (id) ;

alter table rent_points
    add constraint rp_adrs_unq unique (address_id) ;

alter table scooter_model_pricing
    add constraint scooter_model_pricing_scooterModel_id_fk
        foreign key (scooter_model_id)
            references scooter_models (id) ;

alter table scooter_model_pricing
    add constraint mdl_prc_unq unique (scooter_model_id) ;

alter table scooters
    add constraint scooters_currentPoint_id_fk
        foreign key (current_point_id)
            references rent_points (id) ;

alter table scooters
    add constraint scooters_model_id_fk
        foreign key (model_id)
            references scooter_models (id) ;

alter table subscriptions
    add constraint subscriptions_owner_id_fk
        foreign key (owner_id)
            references clients (id) ;


alter table users_roles
    add constraint users_roles_roles_id_fk
        foreign key (roles_id)
            references roles (id) ;

alter table users_roles
    add constraint users_roles_users_id_fk
        foreign key (users_id)
            references users (id);