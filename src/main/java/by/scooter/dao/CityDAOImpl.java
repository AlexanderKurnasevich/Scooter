package by.scooter.dao;

import by.scooter.api.dao.CityDAO;
import by.scooter.entity.location.City;
import org.springframework.stereotype.Repository;

@Repository
public class CityDAOImpl extends AbstractDAO<City> implements CityDAO {
    @Override
    protected Class<City> getClazz() {
        return City.class;
    }
}
