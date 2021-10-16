package by.scooter.api.dao;

import by.scooter.entity.pricing.ScooterModelPricing;

public interface ScooterModelPricingDAO extends DAO<ScooterModelPricing> {

    ScooterModelPricing getByModelId(Long scooterModelId);
}
