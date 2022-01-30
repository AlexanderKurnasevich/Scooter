package by.scooter.dao;

import by.scooter.api.dao.AddressDAO;
import by.scooter.entity.location.Address;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDAOImpl extends AbstractDAO<Address> implements AddressDAO {

    @Override
    protected Class<Address> getClazz() {
        return Address.class;
    }
}
