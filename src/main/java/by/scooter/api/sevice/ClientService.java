package by.scooter.api.sevice;

import by.scooter.entity.user.Client;

public interface ClientService {
    Client addClient(Client client);

    void removeClient(Long id);

    void updateClient(Long updatedId, Client update);
}
