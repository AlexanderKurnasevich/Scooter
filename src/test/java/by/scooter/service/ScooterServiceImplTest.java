package by.scooter.service;

import by.scooter.api.dao.ScooterDAO;
import by.scooter.api.dao.ScooterModelDAO;
import by.scooter.api.sevice.ScooterModelService;
import by.scooter.api.sevice.ScooterService;
import by.scooter.api.sevice.UtilService;
import by.scooter.dto.vehicle.ScooterDTO;
import by.scooter.dto.vehicle.ScooterFilterDTO;
import by.scooter.dto.vehicle.ScooterModelDTO;
import by.scooter.entity.vehicle.Scooter;
import by.scooter.entity.vehicle.ScooterModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ScooterServiceImplTest {

//    private ScooterService service;
//    private ScooterDAO scooterDAO;
//    private ScooterModelDAO scooterModelDAO;
//    private ModelMapper mapper;
//    private UtilService utilService;
//
//    @BeforeEach
//    public void init() {
//        scooterDAO = Mockito.mock(ScooterDAO.class);
//        scooterModelDAO = Mockito.mock(ScooterModelDAO.class);
//        mapper = Mockito.mock(ModelMapper.class);
//        utilService = Mockito.mock(UtilService.class);
//        service = new ScooterServiceImpl(scooterDAO, scooterModelDAO, mapper, utilService);
//    }
//
//    @Test
//    void getById() {
//        Scooter expected = new Scooter();
//        ScooterDTO mapped = new ScooterDTO();
//        when(scooterDAO.getById(1L)).thenReturn(expected);
//        when(mapper.map(expected, ScooterDTO.class)).thenReturn(mapped);
//
//        assertEquals(mapped, service.getById(1L));
//    }
//
//    @Test
//    void getAll() {
//        List<ScooterDTO> expected = new ArrayList<>();
//        List<Scooter> scooters = new ArrayList<>();
//        when(scooterDAO.getAll()).thenReturn(scooters);
//        when(utilService.convertList(scooters, ScooterDTO.class)).thenReturn(expected);
//
//        assertEquals(expected, service.getAll());
//    }
//
//    @Test
//    void getAllPagination() {
//        List<ScooterDTO> expected = new ArrayList<>();
//        List<Scooter> scooters = new ArrayList<>();
//        when(scooterDAO.getAll(1, 1)).thenReturn(scooters);
//        when(utilService.convertList(scooters, ScooterDTO.class)).thenReturn(expected);
//
//        assertEquals(expected, service.getAll());
//    }
//
//    @Test
//    void getVacantScooters() {
//        List<ScooterDTO> expected = new ArrayList<>();
//        List<Scooter> scooters = new ArrayList<>();
//        ScooterFilterDTO filter = new ScooterFilterDTO();
//
//        when(scooterDAO.getVacant(filter, 1, 1)).thenReturn(scooters);
//        when(utilService.convertList(scooters, ScooterDTO.class)).thenReturn(expected);
//
//        assertEquals(expected, service.getVacantScooters(filter, 1 ,1));
//    }
//
//    @Test
//    void addScooter() {
//        ScooterDTO actual = new ScooterDTO();
//        Scooter scooter = new Scooter();
//        when(mapper.map(actual, Scooter.class)).thenReturn(scooter);
//        when(scooterDAO.save(scooter)).thenReturn(scooter);
//        when(mapper.map(scooter, ScooterDTO.class)).thenReturn(actual);
//
//        assertEquals(actual, service.addScooter(actual));
//    }
//
//    @Test
//    void removeScooter() {
//        service.removeScooter(1L);
//
//        verify(scooterDAO, times(1)).delete(1L);
//    }
//
//    @Test
//    void updateScooter() {
//        ScooterDTO actual = new ScooterDTO();
//        actual.setModelId(1L);
//        ScooterModel model = new ScooterModel();
//        model.setId(1L);
//        Scooter scooter = new Scooter();
//        scooter.setModel(model);
//
//        when(scooterDAO.getById(1L)).thenReturn(scooter);
//        when(scooterModelDAO.getById(1L)).thenReturn(model);
//        when(mapper.map(actual, Scooter.class)).thenReturn(scooter);
//
//        service.updateScooter(1L, actual);
//        verify(scooterDAO, times(1)).getById(1L);
//        verify(scooterModelDAO, times(1)).getById(1L);
//        verify(scooterDAO, times(1)).update(any(Scooter.class));
//    }
//
//    @Test
//    void addMileage() {
//        Scooter scooter = new Scooter();
//        when(scooterDAO.getById(1L)).thenReturn(scooter);
//
//        service.addMileage(1L, 11);
//
//        verify(scooterDAO, times(1)).getById(1L);
//        verify(scooterDAO, times(1)).update(any(Scooter.class));
//    }
//
//    @Test
//    void moveScooter() {
//        ScooterDTO actual = new ScooterDTO();
//        actual.setCurrentPointId(1L);
//        ScooterModel model = new ScooterModel();
//        model.setId(1L);
//        Scooter scooter = new Scooter();
//        scooter.setModel(model);
//
//        when(scooterDAO.getById(1L)).thenReturn(scooter);
//        when(mapper.map(actual, Scooter.class)).thenReturn(scooter);
//        when(scooterModelDAO.getById(1L)).thenReturn(model);
//
//        service.moveScooter(1L, 1L);
//        verify(scooterDAO, times(1)).getById(1L);
//        verify(scooterModelDAO, times(1)).getById(1L);
//        verify(scooterDAO, times(1)).update(any(Scooter.class));
//    }
}