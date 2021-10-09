package by.scooter.dao;

import by.scooter.api.dao.CountryDAO;
import by.scooter.entity.location.Country;
import org.springframework.stereotype.Repository;

@Repository
public class CountryDAOImpl extends AbstractDAO<Country> implements CountryDAO {
    @Override
    protected Class<Country> getClazz() {
        return Country.class;
    }
}
