package by.scooter.service;

import by.scooter.api.dao.DiscountDAO;
import by.scooter.api.dao.ScooterModelPricingDAO;
import by.scooter.api.sevice.PricingService;
import by.scooter.api.sevice.UtilService;
import by.scooter.dto.event.OrderCreateDTO;
import by.scooter.dto.pricing.ScooterModelPricingDTO;
import by.scooter.entity.pricing.Discount;
import by.scooter.entity.pricing.ScooterModelPricing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PricingServiceImplTest {

    private PricingService pricingService;
    private ScooterModelPricingDAO modelPricingDAO;
    private DiscountDAO discountDAO;
    private ModelMapper mapper;
    private UtilService utilService;

    @BeforeEach
    public void init() {
        modelPricingDAO = Mockito.mock(ScooterModelPricingDAO.class);
        discountDAO = Mockito.mock(DiscountDAO.class);
        mapper = Mockito.mock(ModelMapper.class);
        utilService = Mockito.mock(UtilService.class);
        pricingService = new PricingServiceImpl(modelPricingDAO, discountDAO, mapper, utilService);
    }

    @Test
    void getByModelId() {
        ScooterModelPricing modelPricing = new ScooterModelPricing();
        ScooterModelPricingDTO expected = new ScooterModelPricingDTO();

        when(modelPricingDAO.getByModelId(1L)).thenReturn(modelPricing);
        when(mapper.map(modelPricing, ScooterModelPricingDTO.class)).thenReturn(expected);

        assertEquals(expected, pricingService.getByModelId(1L));
    }

    @Test
    void getById() {
        ScooterModelPricing modelPricing = new ScooterModelPricing();
        ScooterModelPricingDTO expected = new ScooterModelPricingDTO();

        when(modelPricingDAO.getById(1L)).thenReturn(modelPricing);
        when(mapper.map(modelPricing, ScooterModelPricingDTO.class)).thenReturn(expected);

        assertEquals(expected, pricingService.getById(1L));
    }

    @Test
    void getAll() {
        List<ScooterModelPricing> pricing = new ArrayList<>();
        List<ScooterModelPricingDTO> expected = new ArrayList<>();

        when(modelPricingDAO.getAll(1, 1)).thenReturn(pricing);
        when(utilService.convertList(pricing, ScooterModelPricingDTO.class)).thenReturn(expected);

        assertEquals(expected, pricingService.getAll(1, 1));
    }

    @Test
    void addScooterModelPricing() {
        ScooterModelPricing modelPricing = new ScooterModelPricing();
        ScooterModelPricingDTO expected = new ScooterModelPricingDTO();

        when(modelPricingDAO.save(modelPricing)).thenReturn(modelPricing);
        when(mapper.map(expected, ScooterModelPricing.class)).thenReturn(modelPricing);
        when(mapper.map(modelPricing, ScooterModelPricingDTO.class)).thenReturn(expected);

        assertEquals(expected, pricingService.addScooterModelPricing(expected));
    }

    @Test
    void removeScooterModelPricing() {
        pricingService.removeScooterModelPricing(1L);
        verify(modelPricingDAO, times(1)).delete(1L);
    }

    @Test
    void updateScooterModelPricing() {
        ScooterModelPricing expected = new ScooterModelPricing();
        ScooterModelPricingDTO given = new ScooterModelPricingDTO();

        when(mapper.map(given, ScooterModelPricing.class)).thenReturn(expected);
        doNothing().when(modelPricingDAO).update(expected);

        pricingService.updateScooterModelPricing(1L, given);
        verify(modelPricingDAO, times(1)).update(expected);
    }

    @Test
    void calculatePrice() {
        OrderCreateDTO actual = new OrderCreateDTO();
        actual.setEventStart(LocalDateTime.now());
        actual.setEventEnd(LocalDateTime.now().plusDays(1).plusHours(1).plusMinutes(1));
        actual.setScooterModelId(1L);
        actual.setPromoCode("code");

        ScooterModelPricing pricing = new ScooterModelPricing();
        pricing.setDayPrice(1000.1F);
        pricing.setHourPrice(100.01F);
        pricing.setMinutePrice(10.001F);

        Discount discount = new Discount();
        discount.setDiscountFactor(0.9F);
        discount.setExpireDate(LocalDate.now().plusDays(14));

        when(modelPricingDAO.getByModelId(1L)).thenReturn(pricing);
        when(discountDAO.getByPromoCode("code")).thenReturn(discount);

        assertEquals(999.1F, pricingService.calculatePrice(actual), 0.001);

    }
}