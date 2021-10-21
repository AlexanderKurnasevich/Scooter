package by.scooter.dao;

import by.scooter.api.dao.SubscriptionPricingDAO;
import by.scooter.entity.pricing.SubscriptionPricing;
import by.scooter.entity.pricing.SubscriptionPricing_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.temporal.ChronoUnit;

@Repository
public class SubscriptionPricingDAOImpl extends AbstractDAO<SubscriptionPricing> implements SubscriptionPricingDAO {

    @Override
    public SubscriptionPricing getByUnit(ChronoUnit unit) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SubscriptionPricing> criteriaQuery = builder.createQuery(getClazz());
        Root<SubscriptionPricing> entityRoot = criteriaQuery.from(getClazz());
        criteriaQuery.select(entityRoot).where(builder.equal(entityRoot.get(SubscriptionPricing_.UNIT), unit));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    protected Class<SubscriptionPricing> getClazz() {
        return SubscriptionPricing.class;
    }
}
