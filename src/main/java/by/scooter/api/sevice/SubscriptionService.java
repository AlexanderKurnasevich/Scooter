package by.scooter.api.sevice;

import by.scooter.entity.dto.event.OrderCreateDTO;
import by.scooter.entity.dto.pricing.SubscriptionDTO;

public interface SubscriptionService {
    SubscriptionDTO add(SubscriptionDTO subscriptionDTO);

    void handleOrder(OrderCreateDTO order);
}
