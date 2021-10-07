package by.scooter.dao;

import by.scooter.api.dao.RentPointDAO;
import by.scooter.entity.location.RentPoint;
import by.scooter.entity.location.RentPoint_;
import by.scooter.entity.vehicle.Scooter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
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
    protected Class<RentPoint> getClazz() {
        return RentPoint.class;
    }
}
