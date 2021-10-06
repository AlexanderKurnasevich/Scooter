package by.scooter.dao;

import by.scooter.api.dao.RoleDAO;
import by.scooter.entity.user.Role;

public class RoleDAOImpl extends AbstractDAO<Role> implements RoleDAO {
    @Override
    protected Class<Role> getClazz() {
        return Role.class;
    }
}
