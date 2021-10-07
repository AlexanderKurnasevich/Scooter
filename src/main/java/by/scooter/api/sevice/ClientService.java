package by.scooter.api.sevice;

import by.scooter.entity.event.Order;
import by.scooter.entity.user.Client;

import java.util.List;

public interface ClientService {
    Client getById(Long id);

    Client addClient(Client client);

    void removeClient(Long id);

    void updateClient(Long updatedId, Client update);

    List<Order> ordersByClient(Client client);
}
