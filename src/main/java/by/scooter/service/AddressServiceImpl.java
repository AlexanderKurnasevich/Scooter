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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressDAO addressDAO;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;
    private final ModelMapper mapper;

    @Autowired
    public AddressServiceImpl(AddressDAO addressDAO, CityDAO cityDAO, CountryDAO countryDAO, ModelMapper mapper) {
        this.addressDAO = addressDAO;
        this.cityDAO = cityDAO;
        this.countryDAO = countryDAO;
        this.mapper = mapper;
    }

    @Override
    public Address getById(Long addressId) {
        return addressDAO.getById(addressId);
    }

    @Override
    @Transactional
    public AddressDTO saveRentPointAddress(RentPointDTO dto) {
        Address address = new Address();
        handleAddress(dto, address);
        return mapper.map(addressDAO.save(address), AddressDTO.class);
    }

    @Override
    @Transactional
    public void updateAddress(Long updatedId, RentPointDTO update) {
        Address updated = addressDAO.getById(updatedId);
        handleAddress(update, updated);
        addressDAO.update(updated);
    }

    private void handleAddress(RentPointDTO dto, Address address) {
        Country country = getByCountryNameOrSave(dto.getCountry());
        City city = getByCityNameAndCountryOrSave(dto.getCity(), country);

        address.setNumber(dto.getNumber());
        address.setStreet(dto.getStreet());
        Optional.ofNullable(dto.getPostfix()).ifPresent(address::setPostfix);
        address.setCity(city);
    }

    private Country getByCountryNameOrSave(String name) {
        try {
            return countryDAO.getByCountryName(name);
        } catch (NoResultException ex) {
            Country country = new Country();
            country.setCountryName(name);
            return countryDAO.save(country);
        }
    }

    private City getByCityNameAndCountryOrSave(String name, Country country) {
        try {
            return cityDAO.getByCityNameAndCountry(name, country);
        } catch (NoResultException ex) {
            City city = new City();
            city.setCityName(name);
            city.setCountry(country);
            return cityDAO.save(city);
        }
    }
}
