package by.scooter.service;

import by.scooter.api.dao.ClientDAO;
import by.scooter.api.dao.OrderDAO;
import by.scooter.api.dao.ScooterDAO;
import by.scooter.api.sevice.OrderService;
import by.scooter.api.sevice.UtilService;
import by.scooter.entity.dto.event.OrderDTO;
import by.scooter.entity.event.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;
    private final ModelMapper mapper;
    private final UtilService utilService;

    @Override
    public OrderDTO getById(Long id) {
        return mapper.map(orderDAO.getById(id), OrderDTO.class);
    }

    @Override
    public OrderDTO addOrder(OrderDTO order) {
        return mapper.map(orderDAO.save(mapper.map(order, Order.class)), OrderDTO.class);
    }

    @Override
    public void removeOrder(Long id) {
        orderDAO.delete(id);
    }

    @Override
    public void updateOrder(Long updatedId, OrderDTO update) {
        Order updated = orderDAO.getById(updatedId);
        Optional.ofNullable(update.getMileage()).ifPresent(updated::setMileage);
        orderDAO.update(updated);
    }

    @Override
    public List<OrderDTO> getAll(Integer page, Integer size) {
        return utilService.convertList(orderDAO.getAll(page, size), OrderDTO.class);
    }

    @Override
    public List<OrderDTO> ordersByScooter(Long id, Integer page, Integer size) {
        return utilService.convertList(orderDAO.getOrdersByScooter(id, page, size), OrderDTO.class);
    }

    @Override
    public List<OrderDTO> ordersByClient(Long id, Integer page, Integer size) {
        return utilService.convertList(orderDAO.getByClient(id, page, size), OrderDTO.class);
    }
}
