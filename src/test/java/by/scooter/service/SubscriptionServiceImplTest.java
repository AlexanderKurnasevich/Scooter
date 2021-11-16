package by.scooter.service;

import by.scooter.api.dao.ScooterModelDAO;
import by.scooter.api.dao.SubscriptionDAO;
import by.scooter.api.sevice.SubscriptionPricingService;
import by.scooter.api.sevice.SubscriptionService;
import by.scooter.api.sevice.UtilService;
import by.scooter.dto.event.OrderCreateDTO;
import by.scooter.dto.pricing.SubscriptionDTO;
import by.scooter.dto.vehicle.ScooterModelDTO;
import by.scooter.entity.pricing.Subscription;
import by.scooter.entity.vehicle.ScooterModel;
import by.scooter.exception.DataConsistencyException;
import by.scooter.exception.SubscriptionExpiryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubscriptionServiceImplTest {

    private SubscriptionService service;
    private SubscriptionDAO subscriptionDAO;
    private SubscriptionPricingService pricingService;
    private ModelMapper mapper;
    private UtilService utilService;

    @BeforeEach
    public void init() {
        subscriptionDAO = Mockito.mock(SubscriptionDAO.class);
        pricingService = Mockito.mock(SubscriptionPricingService.class);
        mapper = Mockito.mock(ModelMapper.class);
        utilService = Mockito.mock(UtilService.class);
        service = new SubscriptionServiceImpl(subscriptionDAO, pricingService, mapper, utilService);
    }

    @Test
    void addSubscription() {
        SubscriptionDTO actual = new SubscriptionDTO();
        Subscription subscription = new Subscription();
        when(mapper.map(actual, Subscription.class)).thenReturn(subscription);
        when(subscriptionDAO.save(subscription)).thenReturn(subscription);
        when(mapper.map(subscription, SubscriptionDTO.class)).thenReturn(actual);

        assertEquals(actual, service.addSubscription(actual));
    }

    @Test
    void getById() {
        Subscription expected = new Subscription();
        SubscriptionDTO mapped = new SubscriptionDTO();
        when(subscriptionDAO.getById(1L)).thenReturn(expected);
        when(mapper.map(expected, SubscriptionDTO.class)).thenReturn(mapped);

        assertEquals(mapped, service.getById(1L));
    }

    @Test
    void getAllByClientId() {
        List<SubscriptionDTO> expected = new ArrayList<>();
        List<Subscription> subscriptions = new ArrayList<>();
        when(subscriptionDAO.getAllByClientId(1L, 1, 1)).thenReturn(subscriptions);
        when(utilService.convertList(subscriptions, SubscriptionDTO.class)).thenReturn(expected);

        assertEquals(expected, service.getAllByClientId(1L, 1, 1));
    }

    @Test
    void removeSubscription() {
        service.removeSubscription(1L);

        verify(subscriptionDAO, times(1)).delete(1L);
    }

    @Test
    void renewSubscription() {
        SubscriptionDTO dto = new SubscriptionDTO();
        Subscription actual = new Subscription();
        actual.setUnit(ChronoUnit.DAYS);
        actual.setPrice(10.0F);
        actual.setQuantity(10);
        dto.setUnit(ChronoUnit.DAYS);
        dto.setQuantity(10);
        dto.setExpiryDay(LocalDate.now());

        when(subscriptionDAO.getById(1L)).thenReturn(actual);
        when(pricingService.calculatePrice(dto)).thenReturn(1.1F);

        service.renewSubscription(1L, dto);
        verify(subscriptionDAO, times(1)).update(any(Subscription.class));
        verify(subscriptionDAO, times(1)).getById(1L);
        verify(pricingService, times(1)).calculatePrice(dto);
    }

    @Test
    void handleOrder_ThrowSubscriptionExpiryException() {
        Subscription subscription = new Subscription();
        subscription.setExpiryDay(LocalDate.now().minusDays(1));
        OrderCreateDTO dto = new OrderCreateDTO();
        dto.setSubscriptionId(1L);
        when(subscriptionDAO.getById(1L)).thenReturn(subscription);

        assertThrows(SubscriptionExpiryException.class, () -> {
            service.handleOrder(dto);
        });
    }

    @Test
    void handleOrder_ThrowDataConsistencyException() {
        Subscription subscription = new Subscription();
        subscription.setExpiryDay(LocalDate.now().plusDays(2));
        subscription.setUnit(ChronoUnit.DECADES);
        OrderCreateDTO dto = new OrderCreateDTO();
        dto.setSubscriptionId(1L);
        when(subscriptionDAO.getById(1L)).thenReturn(subscription);

        assertThrows(DataConsistencyException.class, () -> service.handleOrder(dto));
    }
}