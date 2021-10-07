package by.scooter.api.sevice;

import by.scooter.entity.event.Order;

import java.util.List;

public interface OrderService {
    Order getById(Long id);

    Order addOrder(Order order);

    void removeOrder(Long id);

    void updateOrder(Long updatedId, Order update);

    List<Order> ordersByScooter(Long id);
}
