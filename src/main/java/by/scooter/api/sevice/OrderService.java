package by.scooter.api.sevice;

import by.scooter.entity.dto.event.OrderDTO;
import by.scooter.entity.event.Order;

import java.util.List;

public interface OrderService {
    OrderDTO getById(Long id);

    OrderDTO addOrder(OrderDTO order);

    void removeOrder(Long id);

    void updateOrder(Long updatedId, OrderDTO update);

    List<OrderDTO> getAll(Integer page, Integer size);

    List<OrderDTO> ordersByScooter(Long id, Integer page, Integer size);

    List<OrderDTO> ordersByClient(Long id, Integer page, Integer size);
}
