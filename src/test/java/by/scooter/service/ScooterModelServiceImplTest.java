package by.scooter.service;

import by.scooter.api.dao.PasswordResetTokenDAO;
import by.scooter.api.dao.ScooterModelDAO;
import by.scooter.api.dao.UserDAO;
import by.scooter.api.sevice.MailService;
import by.scooter.api.sevice.ScooterModelService;
import by.scooter.api.sevice.UtilService;
import by.scooter.dto.vehicle.ScooterModelDTO;
import by.scooter.entity.vehicle.ScooterModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ScooterModelServiceImplTest {

    private ScooterModelService service;
    private ScooterModelDAO scooterModelDAO;
    private ModelMapper mapper;
    private UtilService utilService;

    @BeforeEach
    public void init() {
        scooterModelDAO = Mockito.mock(ScooterModelDAO.class);
        mapper = Mockito.mock(ModelMapper.class);
        utilService = Mockito.mock(UtilService.class);
        service = new ScooterModelServiceImpl(scooterModelDAO, mapper, utilService);
    }

    @Test
    void getById() {
        ScooterModel expected = new ScooterModel();
        ScooterModelDTO mapped = new ScooterModelDTO();
        when(scooterModelDAO.getById(1L)).thenReturn(expected);
        when(mapper.map(expected, ScooterModelDTO.class)).thenReturn(mapped);

        assertEquals(mapped, service.getById(1L));
    }

    @Test
    void addScooterModel() {
        ScooterModelDTO actual = new ScooterModelDTO();
        ScooterModel model = new ScooterModel();
        when(mapper.map(actual, ScooterModel.class)).thenReturn(model);
        when(scooterModelDAO.save(model)).thenReturn(model);
        when(mapper.map(model, ScooterModelDTO.class)).thenReturn(actual);

        assertEquals(actual, service.addScooterModel(actual));
    }

    @Test
    void removeScooterModel() {
        service.removeScooterModel(1L);

        verify(scooterModelDAO, times(1)).delete(1L);
    }

    @Test
    void updateScooterModel() {
        ScooterModelDTO actual = new ScooterModelDTO();
        ScooterModel model = new ScooterModel();
        when(scooterModelDAO.getById(1L)).thenReturn(model);
        when(mapper.map(actual, ScooterModel.class)).thenReturn(model);

        service.updateScooterModel(1L, actual);
        verify(scooterModelDAO, times(1)).getById(1L);
        verify(scooterModelDAO, times(1)).update(any(ScooterModel.class));
    }

    @Test
    void getAll() {
        List<ScooterModelDTO> expected = new ArrayList<>();
        List<ScooterModel> scooterModels = new ArrayList<>();
        when(scooterModelDAO.getAll()).thenReturn(scooterModels);
        when(utilService.convertList(scooterModels, ScooterModelDTO.class)).thenReturn(expected);

        assertEquals(expected, service.getAll());
    }

    @Test
    void getAllPagination() {
        List<ScooterModelDTO> expected = new ArrayList<>();
        List<ScooterModel> scooterModels = new ArrayList<>();
        when(scooterModelDAO.getAll(1, 1)).thenReturn(scooterModels);
        when(utilService.convertList(scooterModels, ScooterModelDTO.class)).thenReturn(expected);

        assertEquals(expected, service.getAll());
    }
}