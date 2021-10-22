package by.scooter.api.dao;

import by.scooter.entity.pricing.Subscription;

import java.util.List;

public interface SubscriptionDAO extends DAO<Subscription> {
    List<Subscription> getAllByClientId(Long clientId, Integer page, Integer size);
}
