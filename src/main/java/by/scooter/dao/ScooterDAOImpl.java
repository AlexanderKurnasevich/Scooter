package by.scooter.dao;

import by.scooter.api.dao.ScooterDAO;
import by.scooter.entity.dto.vehicle.ScooterDTO;
import by.scooter.entity.dto.vehicle.ScooterFilterDTO;
import by.scooter.entity.location.RentPoint;
import by.scooter.entity.vehicle.Scooter;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScooterDAOImpl extends AbstractDAO<Scooter> implements ScooterDAO {
    @Override
    public List<ScooterDTO> getAll(ScooterFilterDTO filter, Integer page, Integer size) {
        /*CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Scooter> criteriaQuery = builder.createQuery(getClazz());
        Root<Scooter> entityRoot = criteriaQuery.from(getClazz());

        if (filter.getModelId() != null || filter.getRentPoint() != null) {
            Predicate predicate = makePredicate(builder, entityRoot, filter);
            criteriaQuery.select(entityRoot).where(predicate);
        } else {
            criteriaQuery.select(entityRoot);
        }

        if (filter.getSortedColumn() != null) {
            criteriaQuery.orderBy(builder.asc(entityRoot.get(filter.getSortedColumn())));
        }

        TypedQuery<Order> query = entityManager.createQuery(criteriaQuery);
        if (page != null && size != null) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }

        return query.getResultList();
    }

    private Predicate makePredicate(CriteriaBuilder builder, Root<Order> entityRoot, OrderFilterDTO filter) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getStatus() != null) {
            predicates.add(builder.equal(entityRoot.get(Order_.STATUS), filter.getStatus()));
        }
        if (filter.getDateFrom() != null) {
            predicates.add(builder.greaterThanOrEqualTo(entityRoot.get(filter.getDateColumn()), filter.getDateFrom()));
        }
        if (filter.getDateTo() != null) {
            predicates.add(builder.lessThanOrEqualTo(entityRoot.get(filter.getDateColumn()), filter.getDateTo()));
        }
        return builder.and(predicates.toArray(new Predicate[predicates.size()]));*/
        return null;
    }

    @Override
    protected Class<Scooter> getClazz() {
        return Scooter.class;
    }
}
