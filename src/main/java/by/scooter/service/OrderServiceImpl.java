package by.scooter.service;

import by.scooter.api.dao.OrderDAO;
import by.scooter.api.sevice.*;
import by.scooter.dto.event.OrderCreateDTO;
import by.scooter.dto.event.OrderDTO;
import by.scooter.dto.vehicle.ScooterFilterDTO;
import by.scooter.entity.event.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;
    private final ModelMapper mapper;
    private final UtilService utilService;
    private final ScooterService scooterService;
    private final ClientService clientService;
    private final PricingService pricingService;
    private final SubscriptionService subscriptionService;

    @Override
    public OrderDTO getById(Long id) {
        return mapper.map(orderDAO.getById(id), OrderDTO.class);
    }

    @Override
    @Transactional
    public OrderDTO addOrder(OrderCreateDTO order, String promoCode) {
        order.setClientId(clientService.getAuthorizedClient().getId());

        if(order.getSubscriptionId() != null) {
            subscriptionService.handleOrder(order);
        } else {
            order.setPrice(pricingService.calculatePrice(order, promoCode));
        }

        order.setScooterId(scooterService.getVacantScooters(mapper
                .map(order, ScooterFilterDTO.class), 1, 1).get(0).getId());
        return mapper.map(orderDAO.save(mapper.map(order, Order.class)), OrderDTO.class);
    }

    @Override
    public void removeOrder(Long id) {
        orderDAO.delete(id);
    }

    @Override
    @Transactional
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

    @Override
    @Transactional
    public void handleOrder(OrderDTO order, Long rentPointId) {
        updateOrder(order.getId(), order);
        scooterService.addMileage(order.getScooterId(), order.getMileage());
        scooterService.moveScooter(order.getScooterId(), rentPointId);
    }
}
