package by.scooter.api.sevice;

import by.scooter.entity.pricing.RentPointPricing;
import by.scooter.entity.pricing.ScooterModelPricing;
import by.scooter.entity.pricing.SpecialModelPricing;

public interface PricingService {
    RentPointPricing addRentPointPricing(RentPointPricing pricing);

    RentPointPricing getRentPointPricingByPointId(Long rentPointId);

    void addSpecialPricing(Long rentPointId, SpecialModelPricing... pricingSet);

    void removeRentPointPricing(Long id);

    void updateRentPointPricing(RentPointPricing update);

    void updateRentPointPricing(Long updatedId, RentPointPricing update);

    ScooterModelPricing addScooterModelPricing(ScooterModelPricing pricing);

    void removeScooterModelPricing(Long id);

    void updateScooterModelPricing(Long updatedId, ScooterModelPricing update);
}
