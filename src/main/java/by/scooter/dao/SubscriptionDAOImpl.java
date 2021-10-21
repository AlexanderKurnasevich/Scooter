package by.scooter.dao;

import by.scooter.api.dao.SubscriptionDAO;
import by.scooter.entity.pricing.Subscription;
import org.springframework.stereotype.Repository;

@Repository
public class SubscriptionDAOImpl extends AbstractDAO<Subscription> implements SubscriptionDAO {

    @Override
    protected Class<Subscription> getClazz() {
        return Subscription.class;
    }
}
