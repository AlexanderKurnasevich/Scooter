package by.scooter.api.dao;

import by.scooter.entity.enumerator.RoleValue;
import by.scooter.entity.user.Role;

public interface RoleDAO extends DAO<Role> {
    Role getByRole(RoleValue value);
}
