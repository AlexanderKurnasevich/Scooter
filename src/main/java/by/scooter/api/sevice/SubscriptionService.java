package by.scooter.api.sevice;

import by.scooter.entity.dto.event.OrderCreateDTO;
import by.scooter.entity.dto.pricing.SubscriptionDTO;

import java.util.List;

public interface SubscriptionService {

    SubscriptionDTO addSubscription(SubscriptionDTO subscriptionDTO);

    void handleOrder(OrderCreateDTO order);

    SubscriptionDTO getById(Long id);

    List<SubscriptionDTO> getAllByClientId(Long clientId, Integer page, Integer size);

    void removeSubscription(Long id);

    void renewSubscription(Long id, SubscriptionDTO subscription);
}
