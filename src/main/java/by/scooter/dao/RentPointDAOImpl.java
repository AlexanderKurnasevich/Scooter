package by.scooter.dao;

import by.scooter.api.dao.RentPointDAO;
import by.scooter.entity.dto.location.RentPointFilterDTO;
import by.scooter.entity.location.*;
import by.scooter.entity.vehicle.Scooter;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RentPointDAOImpl extends AbstractDAO<RentPoint> implements RentPointDAO {

    @Override
    public List<Scooter> getScooters(Long pointId, Integer page, Integer size) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Scooter> criteriaQuery = cb.createQuery(Scooter.class);

        Root<RentPoint> pointRoot = criteriaQuery.from(RentPoint.class);
        Join<RentPoint, Scooter> scooterJoin = pointRoot.join(RentPoint_.SCOOTERS);
        criteriaQuery.where(cb.equal(pointRoot.get(RentPoint_.id), pointId));
        TypedQuery<Scooter> query = entityManager.createQuery(criteriaQuery.select(scooterJoin));

        if (page != null && size != null) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }

        return query.getResultList();
    }

    @Override
    public List<RentPoint> getAll(RentPointFilterDTO filter, Integer page, Integer size) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RentPoint> criteriaQuery = builder.createQuery(getClazz());
        Root<RentPoint> entityRoot = criteriaQuery.from(getClazz());
        criteriaQuery.select(entityRoot);

        if (filter.getCountry() != null || filter.getCity() != null) {
            Predicate predicate = makePredicate(builder, entityRoot, filter);
            criteriaQuery.select(entityRoot).where(predicate);
        } else {
            criteriaQuery.select(entityRoot);
        }

        if (filter.getSortedColumn() != null) {
            criteriaQuery.orderBy(builder.asc(entityRoot.get(filter.getSortedColumn())));
        }

        TypedQuery<RentPoint> query = entityManager.createQuery(criteriaQuery);
        if (page != null && size != null) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }
        return query.getResultList();
    }

    private Predicate makePredicate(CriteriaBuilder builder, Root<RentPoint> entityRoot, RentPointFilterDTO filter) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getCountry() != null) {
            predicates.add(builder.equal(entityRoot.get(RentPoint_.ADDRESS).get(Address_.CITY).get(City_.COUNTRY)
                    .get(Country_.COUNTRY_NAME), filter.getCountry()));
        }
        if (filter.getCity() != null) {
            predicates.add(builder.equal(entityRoot.get(RentPoint_.ADDRESS).get(Address_.CITY).get(City_.COUNTRY)
                    .get(Country_.COUNTRY_NAME), filter.getCity()));
        }
        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    @Override
    protected Class<RentPoint> getClazz() {
        return RentPoint.class;
    }
}
