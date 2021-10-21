package by.scooter.api.sevice;

import by.scooter.entity.dto.pricing.SubscriptionDTO;

public interface SubscriptionPricingService {
    Float calculatePrice(SubscriptionDTO dto);
}
