package by.scooter.service;

import by.scooter.api.dao.AddressDAO;
import by.scooter.api.dao.CityDAO;
import by.scooter.api.dao.CountryDAO;
import by.scooter.api.sevice.AddressService;
import by.scooter.dto.location.AddressDTO;
import by.scooter.dto.location.RentPointDTO;
import by.scooter.entity.location.Address;
import by.scooter.entity.location.City;
import by.scooter.entity.location.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import javax.persistence.NoResultException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddressServiceImplTest {

    private AddressService addressService;
    private AddressDAO addressDAO;
    private CityDAO cityDAO;
    private CountryDAO countryDAO;
    private ModelMapper mapper;

    @BeforeEach
    public void init() {
        addressDAO = Mockito.mock(AddressDAO.class);
        cityDAO = Mockito.mock(CityDAO.class);
        countryDAO = Mockito.mock(CountryDAO.class);
        mapper = Mockito.mock(ModelMapper.class);
        addressService = new AddressServiceImpl(addressDAO, cityDAO, countryDAO, mapper);
    }

    @Test
    void saveRentPointAddress() {
        Country expectedCountry = new Country();
        expectedCountry.setCountryName("Country");
        expectedCountry.setId(1L);

        City expectedCity = new City();
        expectedCity.setCountry(expectedCountry);
        expectedCity.setCityName("City");
        expectedCity.setId(1L);

        Address expectedAddress = new Address();
        expectedAddress.setCity(expectedCity);
        expectedAddress.setStreet("st. Street");
        expectedAddress.setNumber((short) 1);
        expectedAddress.setId(1L);

        RentPointDTO dto = RentPointDTO.builder()
                .country("Country").city("City").street("st. Street").number((short) 1).build();
        AddressDTO expected = AddressDTO.builder()
                .id(1L).country("Country").city("City").street("st. Street").number((short) 1).build();

        when(countryDAO.getByCountryName("Country")).thenReturn(expectedCountry);
        when(cityDAO.getByCityNameAndCountry("City", expectedCountry)).thenReturn(expectedCity);
        when(addressDAO.save(any(Address.class))).thenReturn(expectedAddress);
        when(mapper.map(expectedAddress, AddressDTO.class)).thenReturn(expected);

        assertEquals(expected, addressService.saveRentPointAddress(dto));
    }

    @Test
    void saveRentPointAddress_NoResultExceptionHandling() {
        Country expectedCountry = new Country();
        expectedCountry.setCountryName("Country");
        expectedCountry.setId(1L);

        City expectedCity = new City();
        expectedCity.setCountry(expectedCountry);
        expectedCity.setCityName("City");
        expectedCity.setId(1L);

        Address expectedAddress = new Address();
        expectedAddress.setCity(expectedCity);
        expectedAddress.setStreet("st. Street");
        expectedAddress.setNumber((short) 1);
        expectedAddress.setId(1L);

        RentPointDTO dto = RentPointDTO.builder()
                .country("Country").city("City").street("st. Street").number((short) 1).build();
        AddressDTO expected = AddressDTO.builder()
                .id(1L).country("Country").city("City").street("st. Street").number((short) 1).build();

        when(countryDAO.getByCountryName("Country")).thenThrow(new NoResultException());
        when(countryDAO.save(any(Country.class))).thenReturn(expectedCountry);
        when(cityDAO.getByCityNameAndCountry("City", expectedCountry)).thenThrow(new NoResultException());
        when(cityDAO.save(any(City.class))).thenReturn(expectedCity);
        when(addressDAO.save(any(Address.class))).thenReturn(expectedAddress);
        when(mapper.map(expectedAddress, AddressDTO.class)).thenReturn(expected);

        assertEquals(expected, addressService.saveRentPointAddress(dto));
    }
}