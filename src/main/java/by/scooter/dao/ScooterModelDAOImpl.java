package by.scooter.dao;

import by.scooter.api.dao.ScooterModelDAO;
import by.scooter.entity.vehicle.ScooterModel;
import org.springframework.stereotype.Repository;

@Repository
public class ScooterModelDAOImpl extends AbstractDAO<ScooterModel> implements ScooterModelDAO {

    @Override
    protected Class<ScooterModel> getClazz() {
        return ScooterModel.class;
    }
}
