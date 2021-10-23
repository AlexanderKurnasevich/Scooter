package by.scooter.dao;

import by.scooter.api.dao.CountryDAO;
import by.scooter.entity.location.Country;
import by.scooter.entity.location.Country_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class CountryDAOImpl extends AbstractDAO<Country> implements CountryDAO {

    @Override
    public Country getByCountryName(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = builder.createQuery(getClazz());
        Root<Country> entityRoot = criteriaQuery.from(getClazz());
        criteriaQuery.select(entityRoot).where(builder.equal(entityRoot.get(Country_.COUNTRY_NAME), name));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    protected Class<Country> getClazz() {
        return Country.class;
    }
}
