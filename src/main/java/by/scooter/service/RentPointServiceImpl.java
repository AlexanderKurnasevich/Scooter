package by.scooter.service;

import by.scooter.api.dao.RentPointDAO;
import by.scooter.api.sevice.AddressService;
import by.scooter.api.sevice.RentPointService;
import by.scooter.api.sevice.UtilService;
import by.scooter.dto.location.RentPointDTO;
import by.scooter.dto.location.RentPointFilterDTO;
import by.scooter.dto.vehicle.ScooterDTO;
import by.scooter.entity.location.RentPoint;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RentPointServiceImpl implements RentPointService {

    private final RentPointDAO rentPointDAO;
    private final AddressService addressService;
    private final ModelMapper mapper;
    private final UtilService utilService;

    @Autowired
    public RentPointServiceImpl(RentPointDAO rentPointDAO, AddressService addressService, ModelMapper mapper,
                                UtilService utilService) {
        this.rentPointDAO = rentPointDAO;
        this.addressService = addressService;
        this.mapper = mapper;
        this.utilService = utilService;
    }

    @Override
    public RentPointDTO getById(Long id) {
        return mapper.map(rentPointDAO.getById(id), RentPointDTO.class);
    }

    @Override
    public RentPointDTO addRentPoint(RentPointDTO rentPoint) {
        if (rentPoint.getAddressId() == null) {
            rentPoint.setAddressId(addressService.saveRentPointAddress(rentPoint).getId());
        }
        return mapper.map(rentPointDAO.save(mapper.map(rentPoint, RentPoint.class)), RentPointDTO.class);
    }

    @Override
    public void removeRentPoint(Long id) {
        rentPointDAO.delete(id);
    }

    @Override
    public void updateRentPoint(Long updatedId, RentPointDTO update) {
        addressService.updateAddress(update.getAddressId(), update);
    }

    @Override
    public List<RentPointDTO> getAll(RentPointFilterDTO filter, Integer page, Integer size) {
        if (filter == null) {
            filter = new RentPointFilterDTO();
        }
        return utilService.convertList(rentPointDAO.getAll(filter, page, size), RentPointDTO.class);
    }

    @Override
    public List<ScooterDTO> scootersInRentPoint(Long id, Integer page, Integer size) {
        return utilService.convertList(rentPointDAO.getScooters(id, page, size), ScooterDTO.class);
    }
}
