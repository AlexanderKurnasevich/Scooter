package by.scooter.api.sevice;

import by.scooter.entity.dto.event.OrderCreateDTO;
import by.scooter.entity.dto.pricing.ScooterModelPricingDTO;

import java.util.List;

public interface PricingService {

    ScooterModelPricingDTO addScooterModelPricing(ScooterModelPricingDTO pricing);

    ScooterModelPricingDTO getByModelId(Long modelId);

    ScooterModelPricingDTO getById(Long id);

    Float calculatePrice(OrderCreateDTO order, String promoCode);

    void removeScooterModelPricing(Long id);

    void updateScooterModelPricing(Long updatedId, ScooterModelPricingDTO update);

    List<ScooterModelPricingDTO> getAll(Integer page, Integer size);
}
