package by.scooter.service;

import by.scooter.api.dao.RoleDAO;
import by.scooter.api.sevice.RoleService;
import by.scooter.entity.enumerator.RoleValue;
import by.scooter.entity.user.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RoleServiceImplTest {

//    private RoleService roleService;
//    private RoleDAO roleDAO;
//
//    @BeforeEach
//    public void init() {
//        roleDAO = Mockito.mock(RoleDAO.class);
//        roleService = new RoleServiceImpl(roleDAO);
//    }
//
//    @Test
//    void getById() {
//        when(roleDAO.getById(anyLong())).thenReturn(new Role());
//
//        roleService.getById(1L);
//        verify(roleDAO, times(1)).getById(anyLong());
//    }
//
//    @Test
//    void getByValue() {
//        when(roleDAO.getByRole(any(RoleValue.class))).thenReturn(new Role());
//
//        roleService.getByValue(RoleValue.ROLE_USER);
//        verify(roleDAO, times(1)).getByRole(any(RoleValue.class));
//    }
//
//    @Test
//    void addRole() {
//        Role expected = new Role();
//        when(roleDAO.save(expected)).thenReturn(expected);
//
//        assertEquals(expected, roleService.addRole(expected));
//    }
//
//    @Test
//    void removeRole() {
//        doNothing().when(roleDAO).delete(1L);
//
//        roleService.removeRole(1L);
//        verify(roleDAO, times(1)).delete(1L);
//    }
//
//    @Test
//    void updateRole() {
//        Role expected = new Role();
//        doNothing().when(roleDAO).update(any(Role.class));
//        when(roleDAO.getById(1L)).thenReturn(expected);
//
//        roleService.updateRole(1L, expected);
//        verify(roleDAO, times(1)).getById(1L);
//        verify(roleDAO, times(1)).update(expected);
//    }
}