package by.scooter.dao;

import by.scooter.api.dao.ScooterDAO;
import by.scooter.entity.vehicle.Scooter;
import org.springframework.stereotype.Repository;

@Repository
public class ScooterDAOImpl extends AbstractDAO<Scooter> implements ScooterDAO {
    @Override
    protected Class<Scooter> getClazz() {
        return Scooter.class;
    }
}
