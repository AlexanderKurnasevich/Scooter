package by.scooter.dao;

import by.scooter.api.dao.ClientDAO;
import by.scooter.entity.user.Client;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDAOImpl extends AbstractDAO<Client> implements ClientDAO {
    @Override
    protected Class<Client> getClazz() {
        return Client.class;
    }
}
