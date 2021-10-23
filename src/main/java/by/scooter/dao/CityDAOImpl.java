package by.scooter.dao;

import by.scooter.api.dao.CityDAO;
import by.scooter.entity.location.City;
import by.scooter.entity.location.City_;
import by.scooter.entity.location.Country;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class CityDAOImpl extends AbstractDAO<City> implements CityDAO {

    @Override
    public City getByCityNameAndCountry(String name, Country country) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<City> criteriaQuery = builder.createQuery(getClazz());
        Root<City> entityRoot = criteriaQuery.from(getClazz());

        Predicate countryPredicate = builder.equal(entityRoot.get(City_.COUNTRY), country);
        Predicate cityPredicate = builder.equal(entityRoot.get(City_.CITY_NAME), name);
        Predicate predicate = builder.and(cityPredicate, countryPredicate);

        criteriaQuery.select(entityRoot).where(predicate);
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    protected Class<City> getClazz() {
        return City.class;
    }
}
