package by.scooter.api.sevice;

import by.scooter.entity.dto.pricing.SubscriptionDTO;
import by.scooter.entity.dto.pricing.SubscriptionPricingDTO;

import java.util.List;

public interface SubscriptionPricingService {
    Float calculatePrice(SubscriptionDTO dto);

    SubscriptionPricingDTO getByUnit(String unit);

    List<SubscriptionPricingDTO> getAll(Integer page, Integer size);

    SubscriptionPricingDTO addSubscriptionPricing(SubscriptionPricingDTO pricing);

    void updateSubscriptionPricing(Long id, SubscriptionPricingDTO pricing);

    void removeSubscriptionPricing(Long id);
}
