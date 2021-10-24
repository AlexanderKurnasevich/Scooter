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
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressDAO addressDAO;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public AddressDTO saveRentPointAddress(RentPointDTO dto) {
        Address address = new Address();
        Country country = getByCountryNameOrSave(dto.getCountry());
        City city = getByCityNameAndCountryOrSave(dto.getCity(), country);

        address.setNumber(dto.getNumber());
        address.setStreet(dto.getStreet());
        Optional.ofNullable(dto.getPostfix()).ifPresent(address::setPostfix);
        address.setCity(city);

        return mapper.map(addressDAO.save(address), AddressDTO.class);
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
