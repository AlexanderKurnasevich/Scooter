package by.scooter.dao;

import by.scooter.api.dao.RentPointPricingDAO;
import by.scooter.entity.location.RentPoint_;
import by.scooter.entity.pricing.RentPointPricing;
import by.scooter.entity.pricing.RentPointPricing_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class RentPointPricingDAOImpl extends AbstractDAO<RentPointPricing> implements RentPointPricingDAO {
    @Override
    public RentPointPricing getByRentPointId(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RentPointPricing> criteriaQuery = builder.createQuery(getClazz());
        Root<RentPointPricing> entityRoot = criteriaQuery.from(getClazz());
        criteriaQuery.select(entityRoot)
                .where(builder.equal(entityRoot.get(RentPointPricing_.RENT_POINT).get(RentPoint_.ID), id));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    protected Class<RentPointPricing> getClazz() {
        return RentPointPricing.class;
    }
}
