package by.scooter.api.sevice;

import by.scooter.entity.dto.pricing.RentPointPricingDTO;
import by.scooter.entity.dto.pricing.ScooterModelPricingDTO;
import by.scooter.entity.dto.pricing.SpecialModelPricingDTO;

public interface PricingService {
    RentPointPricingDTO addRentPointPricing(RentPointPricingDTO pricing);

    RentPointPricingDTO getRentPointPricingByPointId(Long rentPointId);

    void updateRentPointPricing(RentPointPricingDTO update);

    void updateRentPointPricing(Long updatedId, RentPointPricingDTO update);


    void addSpecialPricing(Long rentPointId, SpecialModelPricingDTO... pricingArray);

    void removeRentPointPricing(Long id);


    ScooterModelPricingDTO addScooterModelPricing(ScooterModelPricingDTO pricing);

    void removeScooterModelPricing(Long id);

    void updateScooterModelPricing(Long updatedId, ScooterModelPricingDTO update);
}
