package by.scooter.api.dao;

import by.scooter.entity.location.City;
import by.scooter.entity.location.Country;

public interface CityDAO extends DAO<City> {

    City getByCityNameAndCountry(String name, Country country);
}
