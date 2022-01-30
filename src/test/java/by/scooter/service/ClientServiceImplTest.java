package by.scooter.service;

import by.scooter.api.dao.ClientDAO;
import by.scooter.api.dao.RoleDAO;
import by.scooter.api.sevice.ClientService;
import by.scooter.api.sevice.UserService;
import by.scooter.api.sevice.UtilService;
import by.scooter.dto.user.ClientInfoDTO;
import by.scooter.dto.user.ClientUserDTO;
import by.scooter.dto.user.UserInfoDTO;
import by.scooter.entity.enumerator.RoleValue;
import by.scooter.entity.user.Client;
import by.scooter.entity.user.Role;
import by.scooter.entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceImplTest {

    private ClientService clientService;
    private ClientDAO clientDAO;
    private RoleDAO roleDAO;
    private ModelMapper mapper;
    private PasswordEncoder encoder;
    private UtilService utilService;
    private UserService userService;

    @BeforeEach
    public void init() {
        clientDAO = Mockito.mock(ClientDAO.class);
        roleDAO = Mockito.mock(RoleDAO.class);
        mapper = Mockito.mock(ModelMapper.class);
        encoder = Mockito.mock(PasswordEncoder.class);
        utilService = Mockito.mock(UtilService.class);
        userService = Mockito.mock(UserService.class);
        clientService = new ClientServiceImpl(clientDAO, roleDAO, mapper, encoder, utilService, userService);
    }

    @Test
    void getById() {
        when(clientDAO.getById(anyLong())).thenReturn(any(Client.class));
        clientService.getById(1L);
        verify(clientDAO, times(1)).getById(anyLong());
    }

    @Test
    void getAuthorizedClient() {
        Client client = new Client();
        ClientInfoDTO clientInfoDTO = new ClientInfoDTO();
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUsername("user");
        userInfoDTO.setId(1L);

        when(clientDAO.getByUsername("user")).thenReturn(client);
        when(userService.getAuthorizedUser()).thenReturn(userInfoDTO);
        when(mapper.map(client, ClientInfoDTO.class)).thenReturn(clientInfoDTO);
        clientService.getAuthorizedClient();
        verify(clientDAO, times(1)).getByUsername("user");
        verify(mapper, times(1)).map(client, ClientInfoDTO.class);
        verify(userService, times(1)).getAuthorizedUser();
    }

    @Test
    void getAll() {
        List<Client> list = new ArrayList<>();

        when(clientDAO.getAll(1, 1)).thenReturn(list);
        clientService.getAll(1, 1);
        verify(clientDAO, times(1)).getAll(1, 1);
        verify(utilService, times(1)).convertList(list, ClientInfoDTO.class);
    }

    @Test
    void addClient() {
        ClientUserDTO dto = new ClientUserDTO();
        dto.setPassword("pass");
        ClientInfoDTO infoDTO = new ClientInfoDTO();
        Client client = new Client();
        User user = new User();
        client.setUser(user);
        Role role = new Role();

        when(encoder.encode("pass")).thenReturn("pass");
        when(mapper.map(dto, Client.class)).thenReturn(client);
        when(roleDAO.getByRole(any(RoleValue.class))).thenReturn(role);
        when(clientDAO.save(client)).thenReturn(client);
        when(mapper.map(client, ClientInfoDTO.class)).thenReturn(infoDTO);

        clientService.addClient(dto);
        verify(encoder, times(1)).encode("pass");
        verify(roleDAO, times(1)).getByRole(any(RoleValue.class));
        verify(clientDAO, times(1)).save(client);
        verify(mapper, times(1)).map(dto, Client.class);
        verify(mapper, times(1)).map(client, ClientInfoDTO.class);
    }

    @Test
    void removeClient() {
        clientService.removeClient(1L);
        verify(clientDAO, times(1)).delete(1L);
    }

    @Test
    void updateClient() {
        Client expected = new Client();
        User clientUser = new User();
        UserInfoDTO user = new UserInfoDTO();
        expected.setUser(clientUser);
        clientUser.setId(1L);
        user.setId(1L);

        when(clientDAO.getById(1L)).thenReturn(expected);
        when(userService.getAuthorizedUser()).thenReturn(user);

        clientService.updateClient(1L, new ClientUserDTO());
        verify(clientDAO, times(1)).update(any(Client.class));
    }

    @Test
    void checkOwner() {
        Client expected = new Client();
        User clientUser = new User();
        UserInfoDTO user = new UserInfoDTO();
        expected.setUser(clientUser);
        clientUser.setId(1L);
        user.setId(1L);

        when(clientDAO.getById(1L)).thenReturn(expected);
        when(userService.getAuthorizedUser()).thenReturn(user);

        assertEquals(expected, clientService.checkOwner(1L));
    }

    @Test
    void checkOwner_NotAllowed_ThrowAccessDeniedException() {
        Client expected = new Client();
        User clientUser = new User();
        UserInfoDTO user = new UserInfoDTO();
        expected.setUser(clientUser);
        clientUser.setId(1L);
        user.setId(2L);

        when(clientDAO.getById(1L)).thenReturn(expected);
        when(userService.getAuthorizedUser()).thenReturn(user);

        Exception exception = assertThrows(AccessDeniedException.class, () -> {
            clientService.checkOwner(1L);
        });

        String expectedMessage = "Client isn't an owner";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}