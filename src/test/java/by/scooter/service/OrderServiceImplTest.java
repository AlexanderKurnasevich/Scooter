package by.scooter.service;

import by.scooter.api.dao.OrderDAO;
import by.scooter.api.dao.RentPointDAO;
import by.scooter.api.dao.ScooterDAO;
import by.scooter.api.sevice.*;
import by.scooter.dto.event.OrderCreateDTO;
import by.scooter.dto.event.OrderDTO;
import by.scooter.dto.user.ClientInfoDTO;
import by.scooter.dto.vehicle.ScooterDTO;
import by.scooter.dto.vehicle.ScooterFilterDTO;
import by.scooter.entity.event.Order;
import by.scooter.entity.vehicle.Scooter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    private OrderService orderService;
    private OrderDAO orderDAO;
    private ModelMapper mapper;
    private UtilService utilService;
    private ScooterService scooterService;
    private RentPointDAO rentPointDAO;
    private ClientService clientService;
    private PricingService pricingService;
    private SubscriptionService subscriptionService;

    @BeforeEach
    public void init() {
        orderDAO = Mockito.mock(OrderDAO.class);
        mapper = Mockito.mock(ModelMapper.class);
        utilService = Mockito.mock(UtilService.class);
        scooterService = Mockito.mock(ScooterService.class);
        rentPointDAO = Mockito.mock(RentPointDAO.class);
        clientService = Mockito.mock(ClientService.class);
        pricingService = Mockito.mock(PricingService.class);
        subscriptionService = Mockito.mock(SubscriptionService.class);
        orderService =
                new OrderServiceImpl(orderDAO, mapper, utilService,
                scooterService, rentPointDAO, clientService, pricingService, subscriptionService);
    }

    @Test
    void getById() {
        Order order = new Order();
        OrderDTO expected = new OrderDTO();
        when(orderDAO.getById(1L)).thenReturn(order);
        when(mapper.map(order, OrderDTO.class)).thenReturn(expected);

        assertEquals(expected, orderService.getById(1L));

    }

    @Test
    void addOrder_SubscriptionPresent() {
        OrderCreateDTO given = new OrderCreateDTO();
        OrderDTO expected = new OrderDTO();
        given.setSubscriptionId(1L);

        ClientInfoDTO clientInfoDTO = new ClientInfoDTO();
        Order mapped = new Order();

        List<ScooterDTO> scooters = new ArrayList<>();
        ScooterFilterDTO filterDTO = new ScooterFilterDTO();
        ScooterDTO scooter = new ScooterDTO();
        scooter.setId(1L);
        scooters.add(scooter);

        when(clientService.getAuthorizedClient()).thenReturn(clientInfoDTO);
        doNothing().when(subscriptionService).handleOrder(given);
        when(mapper.map(given, ScooterFilterDTO.class)).thenReturn(filterDTO);
        when(scooterService.getVacantScooters(any(ScooterFilterDTO.class), eq(1), eq(1)))
                .thenReturn(scooters);
        when(mapper.map(given, Order.class)).thenReturn(mapped);
        when(orderDAO.save(mapped)).thenReturn(mapped);
        when(mapper.map(mapped, OrderDTO.class)).thenReturn(expected);

        verify(pricingService, times(0)).calculatePrice(given);
        assertEquals(expected, orderService.addOrder(given));
    }

    @Test
    void addOrder_SubscriptionNotPresent() {
        OrderCreateDTO given = new OrderCreateDTO();
        OrderDTO expected = new OrderDTO();

        ClientInfoDTO clientInfoDTO = new ClientInfoDTO();
        Order mapped = new Order();

        List<ScooterDTO> scooters = new ArrayList<>();
        ScooterFilterDTO filterDTO = new ScooterFilterDTO();
        ScooterDTO scooter = new ScooterDTO();
        scooter.setId(1L);
        scooters.add(scooter);

        when(clientService.getAuthorizedClient()).thenReturn(clientInfoDTO);
        when(pricingService.calculatePrice(given)).thenReturn(0F);
        when(mapper.map(given, ScooterFilterDTO.class)).thenReturn(filterDTO);
        when(scooterService.getVacantScooters(any(ScooterFilterDTO.class), eq(1), eq(1)))
                .thenReturn(scooters);
        when(mapper.map(given, Order.class)).thenReturn(mapped);
        when(orderDAO.save(mapped)).thenReturn(mapped);
        when(mapper.map(mapped, OrderDTO.class)).thenReturn(expected);

        verify(subscriptionService, times(0)).handleOrder(given);
        assertEquals(expected, orderService.addOrder(given));
    }

    @Test
    void removeOrder() {
        orderService.removeOrder(1L);
        verify(orderDAO, times(1)).delete(1L);
    }

    @Test
    void updateOrder() {
        when(orderDAO.getById(1L)).thenReturn(new Order());

        orderService.updateOrder(1L, new OrderDTO());
        verify(orderDAO, times(1)).getById(1L);
        verify(orderDAO, times(1)).update(any(Order.class));
    }

    @Test
    void getAll() {
        List<OrderDTO> expected = new ArrayList<>();
        List<Order> list = new ArrayList<>();

        when(orderDAO.getAll(1, 1)).thenReturn(list);
        when(utilService.convertList(list, OrderDTO.class)).thenReturn(expected);

        assertEquals(expected, orderService.getAll(1, 1));
    }

    @Test
    void ordersByScooter() {
        List<OrderDTO> expected = new ArrayList<>();
        List<Order> list = new ArrayList<>();

        when(orderDAO.getOrdersByScooter(1L,1, 1)).thenReturn(list);
        when(utilService.convertList(list, OrderDTO.class)).thenReturn(expected);

        assertEquals(expected, orderService.ordersByScooter(1L, 1, 1));
    }

    @Test
    void ordersByClient() {
        List<OrderDTO> expected = new ArrayList<>();
        List<Order> list = new ArrayList<>();

        when(orderDAO.getByClient(1L,1, 1)).thenReturn(list);
        when(utilService.convertList(list, OrderDTO.class)).thenReturn(expected);

        assertEquals(expected, orderService.ordersByClient(1L, 1, 1));
    }

    @Test
    void handleOrder() {
        OrderDTO givenOrder = new OrderDTO();
        Long givenRentPointId = 1L;
        givenOrder.setId(1L);
        givenOrder.setScooterId(1L);
        givenOrder.setMileage(1);

        when(orderDAO.getById(1L)).thenReturn(new Order());

        orderService.handleOrder(givenOrder, givenRentPointId);
        verify(orderDAO, times(1)).getById(1L);
        verify(orderDAO, times(1)).update(any(Order.class));
        verify(scooterService, times(1)).addMileage(eq(1L), anyInt());
        verify(scooterService, times(1)).moveScooter(eq(1L), anyLong());
    }
}