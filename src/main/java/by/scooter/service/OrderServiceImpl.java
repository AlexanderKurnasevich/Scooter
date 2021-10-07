package by.scooter.service;

import by.scooter.api.dao.OrderDAO;
import by.scooter.api.sevice.OrderService;
import by.scooter.entity.event.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    @Override
    public Order getById(Long id) {
        return null;
    }

    @Override
    public Order addOrder(Order order) {
        return null;
    }

    @Override
    public void removeOrder(Long id) {

    }

    @Override
    public void updateOrder(Long updatedId, Order update) {

    }

    @Override
    public List<Order> ordersByScooter(Long id) {
        return orderDAO.getOrdersByScooter(id, null, null);
    }
}
