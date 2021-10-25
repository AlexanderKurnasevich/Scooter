package by.scooter.service;

import by.scooter.api.dao.ClientDAO;
import by.scooter.api.dao.RoleDAO;
import by.scooter.api.sevice.ClientService;
import by.scooter.api.sevice.UserService;
import by.scooter.api.sevice.UtilService;
import by.scooter.dto.user.ClientInfoDTO;
import by.scooter.dto.user.ClientUserDTO;
import by.scooter.entity.enumerator.RoleValue;
import by.scooter.entity.user.Client;
import by.scooter.entity.user.Role;
import by.scooter.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientDAO clientDAO;
    private final RoleDAO roleDAO;
    private final ModelMapper mapper;
    private final PasswordEncoder encoder;
    private final UtilService utilService;
    private final UserService userService;

    @Override
    public ClientInfoDTO getById(Long id) {
        return mapper.map(clientDAO.getById(id), ClientInfoDTO.class);
    }

    @Override
    public ClientInfoDTO getAuthorizedClient() {
        return mapper.map(clientDAO.getByUsername(userService.getAuthorizedUser().getUsername()), ClientInfoDTO.class);
    }

    @Override
    public List<ClientInfoDTO> getAll(Integer page, Integer size) {
        return utilService.convertList(clientDAO.getAll(page, size), ClientInfoDTO.class);
    }

    @Override
    @Transactional
    public ClientInfoDTO addClient(ClientUserDTO client) {
        client.setPassword(encoder.encode(client.getPassword()));
        Client newClient = mapper.map(client, Client.class);
        Set<Role> roles = new HashSet<>();
        roles.add(roleDAO.getByRole(RoleValue.ROLE_CLIENT));
        newClient.getUser().setRoles(roles);
        return mapper.map(clientDAO.save(newClient), ClientInfoDTO.class);
    }

    @Override
    @Transactional
    public void removeClient(Long id) {
        clientDAO.delete(id);
    }

    @Override
    @Transactional
    public void updateClient(Long updatedId, ClientInfoDTO update) {
        Client updated = checkOwner(updatedId);
        User user = updated.getUser();
        Optional.ofNullable(update.getEmail()).ifPresent(user::setEmail);
        Optional.ofNullable(update.getFirstName()).ifPresent(updated::setFirstName);
        Optional.ofNullable(update.getLastName()).ifPresent(updated::setLastName);
        clientDAO.update(updated);
    }

    @Override
    public Client checkOwner(Long id) throws AccessDeniedException {
        Client checked = clientDAO.getById(id);
        if (!Objects.equals(userService.getAuthorizedUser().getId(), checked.getUser().getId())) {
            throw new AccessDeniedException("Client isn't an owner");
        }
        return checked;
    }
}
