package by.scooter.service;

import by.scooter.api.dao.RentPointDAO;
import by.scooter.api.sevice.AddressService;
import by.scooter.api.sevice.RentPointService;
import by.scooter.api.sevice.UtilService;
import by.scooter.dto.location.AddressDTO;
import by.scooter.dto.location.RentPointDTO;
import by.scooter.dto.location.RentPointFilterDTO;
import by.scooter.dto.vehicle.ScooterDTO;
import by.scooter.entity.location.RentPoint;
import by.scooter.entity.vehicle.Scooter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RentPointServiceImplTest {

    private RentPointService rentPointService;
    private RentPointDAO rentPointDAO;
    private AddressService addressService;
    private ModelMapper mapper;
    private UtilService utilService;

    @BeforeEach
    public void init() {
        rentPointDAO = Mockito.mock(RentPointDAO.class);
        addressService = Mockito.mock(AddressService.class);
        mapper = Mockito.mock(ModelMapper.class);
        utilService = Mockito.mock(UtilService.class);
        rentPointService = new RentPointServiceImpl(rentPointDAO, addressService, mapper, utilService);
    }

    @Test
    void getById() {
        RentPointDTO expected = new RentPointDTO();
        RentPoint rentPoint = new RentPoint();

        when(rentPointDAO.getById(1L)).thenReturn(rentPoint);
        when(mapper.map(rentPoint, RentPointDTO.class)).thenReturn(expected);

        assertEquals(expected, rentPointService.getById(1L));
    }

    @Test
    void addRentPoint() {
        RentPointDTO expected = new RentPointDTO();
        RentPoint mapped = new RentPoint();
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(1L);

        when(addressService.saveRentPointAddress(expected)).thenReturn(addressDTO);
        when(mapper.map(expected, RentPoint.class)).thenReturn(mapped);
        when(rentPointDAO.save(mapped)).thenReturn(mapped);
        when(mapper.map(mapped, RentPointDTO.class)).thenReturn(expected);

        assertEquals(expected, rentPointService.addRentPoint(expected));
    }

    @Test
    void removeRentPoint() {
        rentPointService.removeRentPoint(1L);
        verify(rentPointDAO, times(1)).delete(1L);
    }

    @Test
    void updateRentPoint() {
        RentPointDTO actual = new RentPointDTO();
        actual.setAddressId(1L);

        rentPointService.updateRentPoint(1L, actual);
        verify(addressService, times(1)).updateAddress(1L, actual);
    }

    @Test
    void getAll() {
        List<RentPoint> list = new ArrayList<>();
        List<RentPointDTO> expected = new ArrayList<>();
        when(rentPointDAO.getAll(any(RentPointFilterDTO.class), eq(1), eq(1))).thenReturn(list);
        when(utilService.convertList(list, RentPointDTO.class)).thenReturn(expected);

        assertEquals(expected, rentPointService.getAll(null, 1, 1));
    }

    @Test
    void scootersInRentPoint() {
        List<Scooter> list = new ArrayList<>();
        List<ScooterDTO> expected = new ArrayList<>();
        when(rentPointDAO.getScooters(anyLong(), eq(1), eq(1))).thenReturn(list);
        when(utilService.convertList(list, ScooterDTO.class)).thenReturn(expected);

        assertEquals(expected, rentPointService.scootersInRentPoint(1L, 1, 1));
    }
}