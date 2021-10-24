package by.scooter.service;

import by.scooter.api.dao.OrderDAO;
import by.scooter.api.sevice.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

class OrderServiceImplTest {

    private OrderService orderService;
    private OrderDAO orderDAO;
    private ModelMapper mapper;
    private UtilService utilService;
    private ScooterService scooterService;
    private ClientService clientService;
    private PricingService pricingService;
    private SubscriptionService subscriptionService;

    @BeforeEach
    public void init() {
        orderDAO = Mockito.mock(OrderDAO.class);
        mapper = Mockito.mock(ModelMapper.class);
        utilService = Mockito.mock(UtilService.class);
        scooterService = Mockito.mock(ScooterService.class);
        clientService = Mockito.mock(ClientService.class);
        pricingService = Mockito.mock(PricingService.class);
        subscriptionService = Mockito.mock(SubscriptionService.class);
        orderService = new OrderServiceImpl(orderDAO, mapper, utilService,
                scooterService, clientService, pricingService, subscriptionService);
    }

    @Test
    void getById() {

    }

    @Test
    void addOrder() {
    }

    @Test
    void removeOrder() {
    }

    @Test
    void updateOrder() {
    }

    @Test
    void getAll() {
    }

    @Test
    void ordersByScooter() {
    }

    @Test
    void ordersByClient() {
    }

    @Test
    void handleOrder() {
    }
}