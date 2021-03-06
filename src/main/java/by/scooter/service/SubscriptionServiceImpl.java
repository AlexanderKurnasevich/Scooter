package by.scooter.service;

import by.scooter.api.dao.SubscriptionDAO;
import by.scooter.api.sevice.SubscriptionPricingService;
import by.scooter.api.sevice.SubscriptionService;
import by.scooter.api.sevice.UtilService;
import by.scooter.dto.event.OrderCreateDTO;
import by.scooter.dto.pricing.SubscriptionDTO;
import by.scooter.entity.pricing.Subscription;
import by.scooter.exception.DataConsistencyException;
import by.scooter.exception.SubscriptionException;
import by.scooter.exception.SubscriptionExpiryException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionDAO subscriptionDAO;
    private final SubscriptionPricingService pricingService;
    private final ModelMapper mapper;
    private final UtilService utilService;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionDAO subscriptionDAO, SubscriptionPricingService pricingService,
                                   ModelMapper mapper, UtilService utilService) {
        this.subscriptionDAO = subscriptionDAO;
        this.pricingService = pricingService;
        this.mapper = mapper;
        this.utilService = utilService;
    }

    @Override
    @Transactional
    public SubscriptionDTO addSubscription(SubscriptionDTO dto) {
        dto.setPrice(pricingService.calculatePrice(dto));
        return mapper.map(subscriptionDAO.save(mapper.map(dto, Subscription.class)), SubscriptionDTO.class);
    }

    @Override
    public SubscriptionDTO getById(Long id) {
        return mapper.map(subscriptionDAO.getById(id), SubscriptionDTO.class);
    }

    @Override
    public List<SubscriptionDTO> getAllByClientId(Long clientId, Integer page, Integer size) {
        return utilService.convertList(subscriptionDAO.getAllByClientId(clientId, page, size), SubscriptionDTO.class);
    }

    @Override
    @Transactional
    public void removeSubscription(Long id) {
        subscriptionDAO.delete(id);
    }

    @Override
    @Transactional
    public void renewSubscription(Long id, SubscriptionDTO subscription) {
        Subscription updated = subscriptionDAO.getById(id);

        if (subscription.getUnit().equals(updated.getUnit())) {
            subscription.setPrice(pricingService.calculatePrice(subscription));
            updated.setPrice((float)
                    UtilService.roundUpToXDecimal((updated.getPrice() + subscription.getPrice()), 3));
            updated.setQuantity(updated.getQuantity() + subscription.getQuantity());
            updated.setExpiryDay(subscription.getExpiryDay());
        }

        subscriptionDAO.update(updated);
    }

    @Override
    @Transactional
    public void handleOrder(OrderCreateDTO order) {
        Subscription subscription = subscriptionDAO.getById(order.getSubscriptionId());
        if (LocalDate.now().isAfter(subscription.getExpiryDay())) {
            throw new SubscriptionExpiryException("Subscription is expired");
        }

        switch (subscription.getUnit()) {
            case DAYS -> writeOffDays(subscription, order);
            case HOURS -> writeOffHours(subscription, order);
            case MINUTES -> writeOffMinutes(subscription, order);
            default -> throw new DataConsistencyException("Unexpected ChronoUnit or NULL");
        }
    }

    private void writeOffDays(Subscription subscription, OrderCreateDTO order) {
        var rentDays = UtilService.getRoundedDays(order.getEventStart(), order.getEventEnd());

        if (subscription.getQuantity() >= rentDays) {
            subscription.setQuantity((int) (subscription.getQuantity() - rentDays));
            subscriptionDAO.update(subscription);
        } else {
            throw new SubscriptionException("Not enough subscription days");
        }
    }

    private void writeOffHours(Subscription subscription, OrderCreateDTO order) {
        var rentHours = UtilService.getRoundedHours(order.getEventStart(), order.getEventEnd());

        if (subscription.getQuantity() >= rentHours) {
            subscription.setQuantity((int) (subscription.getQuantity() - rentHours));
            subscriptionDAO.update(subscription);
        } else {
            throw new SubscriptionException("Not enough subscription hours");
        }
    }

    private void writeOffMinutes(Subscription subscription, OrderCreateDTO order) {
        var rentMinutes = ChronoUnit.MINUTES.between(order.getEventStart(), order.getEventEnd());

        if (subscription.getQuantity() >= rentMinutes) {
            subscription.setQuantity((int) (subscription.getQuantity() - rentMinutes));
            subscriptionDAO.update(subscription);
        } else {
            throw new SubscriptionException("Not enough subscription minutes");
        }
    }
}
