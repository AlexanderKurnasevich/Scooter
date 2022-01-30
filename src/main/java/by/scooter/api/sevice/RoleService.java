package by.scooter.api.sevice;

import by.scooter.entity.enumerator.RoleValue;
import by.scooter.entity.user.Role;

public interface RoleService {

    Role getById(Long id);

    Role getByValue(RoleValue value);

    Role addRole(Role role);

    void removeRole(Long id);

    void updateRole(Long updatedId, Role update);
}
