package by.scooter.api.dao;

import by.scooter.entity.event.Order;

import java.util.List;

public interface OrderDAO extends DAO<Order>{

    List<Order> getOrdersByScooter(Long id, Integer page, Integer size);
}
