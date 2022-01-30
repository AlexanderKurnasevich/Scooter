package by.scooter.service;

import by.scooter.api.dao.DiscountDAO;
import by.scooter.api.sevice.DiscountService;
import by.scooter.api.sevice.UtilService;
import by.scooter.dto.pricing.DiscountDTO;
import by.scooter.entity.pricing.Discount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DiscountServiceImplTest {

    private DiscountService discountService;
    private DiscountDAO discountDAO;
    private ModelMapper mapper;
    private UtilService utilService;

    @BeforeEach
    public void init() {
        discountDAO = Mockito.mock(DiscountDAO.class);
        mapper = Mockito.mock(ModelMapper.class);
        utilService = Mockito.mock(UtilService.class);
        discountService = new DiscountServiceImpl(discountDAO, mapper, utilService);
    }

    @Test
    void getById() {
        Discount discount = new Discount();
        DiscountDTO expected = new DiscountDTO();
        when(discountDAO.getById(1L)).thenReturn(discount);
        when(mapper.map(discount, DiscountDTO.class)).thenReturn(expected);

        assertEquals(expected, discountService.getById(1L));
    }

    @Test
    void getAll() {
        List<Discount> discounts = new ArrayList<>();
        List<DiscountDTO> expected = new ArrayList<>();

        when(discountDAO.getAll(1, 1)).thenReturn(discounts);
        when(utilService.convertList(discounts, DiscountDTO.class)).thenReturn(expected);

        assertEquals(expected, discountService.getAll(1,1));
    }

    @Test
    void add() {
        Discount discount = new Discount();
        DiscountDTO expected = new DiscountDTO();
        when(mapper.map(expected, Discount.class)).thenReturn(discount);
        when(discountDAO.save(discount)).thenReturn(discount);
        when(mapper.map(discount, DiscountDTO.class)).thenReturn(expected);

        assertEquals(expected, discountService.add(expected));
    }

    @Test
    void remove() {
        discountService.remove(1L);
        verify(discountDAO, times(1)).delete(1L);
    }

    @Test
    void update() {
        DiscountDTO given = new DiscountDTO();
        Discount expected = new Discount();
        when(mapper.map(given, Discount.class)).thenReturn(expected);

        discountService.update(1L, given);

        verify(mapper, times(1)).map(given, Discount.class);
        verify(discountDAO, times(1)).update(expected);
    }
}