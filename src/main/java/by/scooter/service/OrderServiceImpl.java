package by.scooter.service;

import by.scooter.api.dao.ClientDAO;
import by.scooter.api.dao.OrderDAO;
import by.scooter.api.dao.ScooterDAO;
import by.scooter.api.sevice.OrderService;
import by.scooter.entity.event.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    private final ClientDAO clientDAO;
    private final ScooterDAO scooterDAO;

    @Override
    public Order getById(Long id) {
        return orderDAO.getById(id);
    }

    @Override
    public Order addOrder(Order order) {
        order.setClient(clientDAO.getById(1L));
        order.setEventStart(LocalDateTime.now());
        order.setEventEnd(LocalDateTime.now().plusDays(1));
        order.setScooter(scooterDAO.getById(1L));
        order.setMileage(0);
        return orderDAO.save(order);
    }

    @Override
    public void removeOrder(Long id) {
        orderDAO.delete(id);
    }

    @Override
    public void updateOrder(Long updatedId, Order update) {
        Order updated = orderDAO.getById(updatedId);
        Optional.ofNullable(update.getMileage()).ifPresent(updated::setMileage);
        orderDAO.update(updated);
    }

    @Override
    public List<Order> getAll(Integer page, Integer size) {
        return orderDAO.getAll(page, size);
    }

    @Override
    public List<Order> ordersByScooter(Long id) {
        return orderDAO.getOrdersByScooter(id, null, null);
    }

    @Override
    public List<Order> ordersByClient(Long id, Integer page, Integer size) {
        return orderDAO.getByClient(id, page, size);
    }
}
