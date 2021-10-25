package by.scooter.api.sevice;

import by.scooter.dto.event.OrderCreateDTO;
import by.scooter.dto.pricing.ScooterModelPricingDTO;

import java.util.List;

public interface PricingService {

    ScooterModelPricingDTO addScooterModelPricing(ScooterModelPricingDTO pricing);

    ScooterModelPricingDTO getByModelId(Long modelId);

    ScooterModelPricingDTO getById(Long id);

    Float calculatePrice(OrderCreateDTO order);

    void removeScooterModelPricing(Long id);

    void updateScooterModelPricing(Long updatedId, ScooterModelPricingDTO update);

    List<ScooterModelPricingDTO> getAll(Integer page, Integer size);
}
