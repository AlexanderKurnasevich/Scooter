package by.scooter.dao;

import by.scooter.api.dao.ScooterModelDAO;
import by.scooter.entity.vehicle.ScooterModel;

public class ScooterModelDAOImpl extends AbstractDAO<ScooterModel> implements ScooterModelDAO {
    @Override
    protected Class<ScooterModel> getClazz() {
        return ScooterModel.class;
    }
}
