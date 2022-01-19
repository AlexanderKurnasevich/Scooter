package by.scooter.service;

import by.scooter.api.dao.ScooterModelDAO;
import by.scooter.api.sevice.ScooterModelService;
import by.scooter.api.sevice.UtilService;
import by.scooter.dto.vehicle.ScooterModelDTO;
import by.scooter.entity.vehicle.ScooterModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScooterModelServiceImpl implements ScooterModelService {

    private final ScooterModelDAO scooterModelDAO;
    private final ModelMapper mapper;
    private final UtilService utilService;

    @Autowired
    public ScooterModelServiceImpl(ScooterModelDAO scooterModelDAO, ModelMapper mapper, UtilService utilService) {
        this.scooterModelDAO = scooterModelDAO;
        this.mapper = mapper;
        this.utilService = utilService;
    }

    @Override
    public ScooterModelDTO getById(Long id) {
        return mapper.map(scooterModelDAO.getById(id), ScooterModelDTO.class);
    }

    @Override
    @Transactional
    public ScooterModelDTO addScooterModel(ScooterModelDTO scooterModel) {
        return mapper.map(scooterModelDAO.save(mapper.map(scooterModel, ScooterModel.class)), ScooterModelDTO.class);
    }

    @Override
    @Transactional
    public void removeScooterModel(Long id) {
        scooterModelDAO.delete(id);
    }

    @Override
    @Transactional
    public void updateScooterModel(Long updatedId, ScooterModelDTO update) {
        update.setId(updatedId);
        scooterModelDAO.update(mapper.map(update, ScooterModel.class));
    }

    @Override
    public List<ScooterModelDTO> getAll() {
        return utilService.convertList(scooterModelDAO.getAll(), ScooterModelDTO.class);
    }

    @Override
    public List<ScooterModelDTO> getAll(Integer page, Integer size) {
        return utilService.convertList(scooterModelDAO.getAll(page, size), ScooterModelDTO.class);
    }
}
