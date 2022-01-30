package by.scooter.api.dao;

import by.scooter.entity.pricing.SubscriptionPricing;

import java.time.temporal.ChronoUnit;

public interface SubscriptionPricingDAO extends DAO<SubscriptionPricing> {

    SubscriptionPricing getByUnit(ChronoUnit unit);
}
