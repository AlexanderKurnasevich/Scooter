package by.scooter.api.sevice;

import by.scooter.entity.dto.ClientUserDTO;
import by.scooter.entity.user.Client;

public interface ClientService {
    ClientUserDTO getById(Long id);

    Client addClient(ClientUserDTO client);

    void removeClient(Long id);

    void updateClient(Long updatedId, ClientUserDTO update);
}
