package by.scooter.api.dao;

import by.scooter.entity.user.Client;

public interface ClientDAO extends DAO<Client> {

    Client getByUsername(String username);
}
