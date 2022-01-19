package by.scooter.service;

import by.scooter.api.dao.ScooterModelDAO;
import by.scooter.api.dao.SubscriptionPricingDAO;
import by.scooter.api.sevice.SubscriptionPricingService;
import by.scooter.api.sevice.UtilService;
import by.scooter.dto.pricing.SubscriptionDTO;
import by.scooter.dto.pricing.SubscriptionPricingDTO;
import by.scooter.dto.vehicle.ScooterModelDTO;
import by.scooter.entity.pricing.SubscriptionPricing;
import by.scooter.entity.vehicle.ScooterModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubscriptionPricingServiceImplTest {

//    private SubscriptionPricingService service;
//    private SubscriptionPricingDAO pricingDAO;
//    private ModelMapper mapper;
//    private UtilService utilService;
//
//    @BeforeEach
//    public void init() {
//        pricingDAO = Mockito.mock(SubscriptionPricingDAO.class);
//        mapper = Mockito.mock(ModelMapper.class);
//        utilService = Mockito.mock(UtilService.class);
//        service = new SubscriptionPricingServiceImpl(pricingDAO, mapper, utilService);
//    }
//
//    @Test
//    void calculatePrice() {
//        SubscriptionDTO dto = new SubscriptionDTO();
//        dto.setUnit(ChronoUnit.DAYS);
//        dto.setQuantity(10);
//        SubscriptionPricing actual = new SubscriptionPricing();
//        actual.setPrice(1.1F);
//        when(pricingDAO.getByUnit(ChronoUnit.DAYS)).thenReturn(actual);
//
//        assertEquals(11.0F, service.calculatePrice(dto));
//
//    }
//
//    @Test
//    void getByUnit() {
//        SubscriptionPricing actual = new SubscriptionPricing();
//        SubscriptionPricingDTO expected = new SubscriptionPricingDTO();
//        actual.setPrice(1.1F);
//        expected.setPrice(1.1F);
//        when(pricingDAO.getByUnit(ChronoUnit.DAYS)).thenReturn(actual);
//        when(mapper.map(actual, SubscriptionPricingDTO.class)).thenReturn(expected);
//
//        assertEquals(expected, service.getByUnit("days"));
//    }
//
//    @Test
//    void getAll() {
//        List<SubscriptionPricingDTO> expected = new ArrayList<>();
//        List<SubscriptionPricing> pricing = new ArrayList<>();
//        when(pricingDAO.getAll()).thenReturn(pricing);
//        when(utilService.convertList(pricing, SubscriptionPricingDTO.class)).thenReturn(expected);
//
//        assertEquals(expected, service.getAll(1, 1));
//    }
//
//    @Test
//    void addSubscriptionPricing() {
//        SubscriptionPricingDTO actual = new SubscriptionPricingDTO();
//        SubscriptionPricing pricing = new SubscriptionPricing();
//        when(mapper.map(actual, SubscriptionPricing.class)).thenReturn(pricing);
//        when(pricingDAO.save(pricing)).thenReturn(pricing);
//        when(mapper.map(pricing, SubscriptionPricingDTO.class)).thenReturn(actual);
//
//        assertEquals(actual, service.addSubscriptionPricing(actual));
//    }
//
//    @Test
//    void updateSubscriptionPricing() {
//        SubscriptionPricingDTO actual = new SubscriptionPricingDTO();
//        SubscriptionPricing pricing = new SubscriptionPricing();
//        when(pricingDAO.getById(1L)).thenReturn(pricing);
//        when(mapper.map(actual, SubscriptionPricing.class)).thenReturn(pricing);
//
//        service.updateSubscriptionPricing(1L, actual);
//        verify(pricingDAO, times(1)).getById(1L);
//        verify(pricingDAO, times(1)).update(any(SubscriptionPricing.class));
//    }
//
//    @Test
//    void removeSubscriptionPricing() {
//        service.removeSubscriptionPricing(1L);
//
//        verify(pricingDAO, times(1)).delete(1L);
//    }
}