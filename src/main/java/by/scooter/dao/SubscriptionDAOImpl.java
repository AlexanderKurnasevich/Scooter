package by.scooter.dao;

import by.scooter.api.dao.SubscriptionDAO;
import by.scooter.entity.AbstractEntity_;
import by.scooter.entity.pricing.Subscription;
import by.scooter.entity.pricing.Subscription_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class SubscriptionDAOImpl extends AbstractDAO<Subscription> implements SubscriptionDAO {

    @Override
    public List<Subscription> getAllByClientId(Long clientId, Integer page, Integer size) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Subscription> criteriaQuery = builder.createQuery(getClazz());
        Root<Subscription> entityRoot = criteriaQuery.from(getClazz());
        criteriaQuery.select(entityRoot)
                .where(builder.equal(entityRoot.get(Subscription_.OWNER).get(AbstractEntity_.ID), clientId));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    protected Class<Subscription> getClazz() {
        return Subscription.class;
    }
}
