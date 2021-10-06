package by.scooter.api.sevice;

import by.scooter.entity.event.Order;

public interface OrderService {
    Order addOrder(Order order);

    void removeOrder(Long id);

    void updateOrder(Long updatedId, Order update);
}
