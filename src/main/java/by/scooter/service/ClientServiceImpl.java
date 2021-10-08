package by.scooter.service;

import by.scooter.api.dao.ClientDAO;
import by.scooter.api.dao.RoleDAO;
import by.scooter.api.sevice.ClientService;
import by.scooter.entity.dto.ClientUserDTO;
import by.scooter.entity.enumerator.RoleValue;
import by.scooter.entity.user.Client;
import by.scooter.entity.user.Role;
import by.scooter.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientDAO clientDAO;
    private final RoleDAO roleDAO;
    private final ModelMapper mapper;
    private final PasswordEncoder encoder;

    @Override
    public ClientUserDTO getById(Long id) {
        return mapper.map(clientDAO.getById(id), ClientUserDTO.class);
    }

    @Override
    @Transactional
    public Client addClient(ClientUserDTO client) {
        client.setPassword(encoder.encode(client.getPassword()));
        Client newClient = mapper.map(client, Client.class);
        Set<Role> roles = new HashSet<>();
        roles.add(roleDAO.getByRole(RoleValue.ROLE_CLIENT));
        newClient.getUser().setRoles(roles);
        return clientDAO.save(newClient);
    }

    @Override
    public void removeClient(Long id) {
        clientDAO.delete(id);
    }

    @Override
    public void updateClient(Long updatedId, ClientUserDTO update) {
        Client updated = clientDAO.getById(updatedId);
        Optional.ofNullable(update.getEmail()).ifPresent(updated::setEmail);
        Optional.ofNullable(update.getFirstName()).ifPresent(updated::setFirstName);
        Optional.ofNullable(update.getLastName()).ifPresent(updated::setLastName);
        clientDAO.update(updated);
    }
}
