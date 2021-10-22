package by.scooter.dao;

import by.scooter.api.dao.ScooterModelPricingDAO;
import by.scooter.entity.AbstractEntity_;
import by.scooter.entity.pricing.ScooterModelPricing;
import by.scooter.entity.pricing.ScooterModelPricing_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class ScooterModelPricingDAOImpl extends AbstractDAO<ScooterModelPricing> implements ScooterModelPricingDAO {

    @Override
    public ScooterModelPricing getByModelId(Long scooterModelId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ScooterModelPricing> criteriaQuery = builder.createQuery(getClazz());
        Root<ScooterModelPricing> entityRoot = criteriaQuery.from(getClazz());
        criteriaQuery.select(entityRoot)
                .where(builder.equal(entityRoot.get(ScooterModelPricing_.SCOOTER_MODEL).get(AbstractEntity_.ID),
                        scooterModelId));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    protected Class<ScooterModelPricing> getClazz() {
        return ScooterModelPricing.class;
    }
}
