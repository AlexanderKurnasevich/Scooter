package by.scooter.dao;

import by.scooter.api.dao.MaintenanceDAO;
import by.scooter.entity.event.Maintenance;
import org.springframework.stereotype.Repository;

@Repository
public class MaintenanceDAOImpl extends AbstractDAO<Maintenance> implements MaintenanceDAO {

    @Override
    protected Class<Maintenance> getClazz() {
        return Maintenance.class;
    }
}
