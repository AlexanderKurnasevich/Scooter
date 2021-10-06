package by.scooter.dao;

import by.scooter.api.dao.OrderDAO;
import by.scooter.entity.event.Order;

public class OrderDAOImpl extends AbstractDAO<Order> implements OrderDAO {

    @Override
    protected Class<Order> getClazz() {
        return Order.class;
    }
}
