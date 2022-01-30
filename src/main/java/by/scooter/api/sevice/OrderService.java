package by.scooter.api.sevice;

import by.scooter.dto.event.OrderCreateDTO;
import by.scooter.dto.event.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO getById(Long id);

    OrderDTO addOrder(OrderCreateDTO order);

    void removeOrder(Long id);

    void updateOrder(Long updatedId, OrderDTO update);

    List<OrderDTO> getAll(Integer page, Integer size);

    List<OrderDTO> ordersByScooter(Long id, Integer page, Integer size);

    List<OrderDTO> ordersByClient(Long id, Integer page, Integer size);

    void handleOrder(OrderDTO order, Long rentPointId);
}
