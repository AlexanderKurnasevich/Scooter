package by.scooter.api.sevice;

import by.scooter.entity.user.Role;

public interface RoleService {
    Role addRole(Role role);

    void removeRole(Long id);

    void updateRole(Long updatedId, Role update);
}
