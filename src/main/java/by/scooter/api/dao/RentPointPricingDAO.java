package by.scooter.api.dao;

import by.scooter.entity.pricing.RentPointPricing;

public interface RentPointPricingDAO extends DAO<RentPointPricing> {

    RentPointPricing getByRentPointId(Long id);
}
