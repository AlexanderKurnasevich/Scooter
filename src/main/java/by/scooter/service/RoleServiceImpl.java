package by.scooter.service;

import by.scooter.api.dao.RoleDAO;
import by.scooter.api.sevice.RoleService;
import by.scooter.entity.enumerator.RoleValue;
import by.scooter.entity.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    @Override
    public Role getById(Long id) {
        return roleDAO.getById(id);
    }

    @Override
    public Role getByValue(RoleValue value) {
        return roleDAO.getByRole(value);
    }

    @Override
    @Transactional
    public Role addRole(Role role) {
        return roleDAO.save(role);
    }

    @Override
    @Transactional
    public void removeRole(Long id) {
        roleDAO.delete(id);
    }

    @Override
    @Transactional
    public void updateRole(Long updatedId, Role update) {
        Role updated = roleDAO.getById(updatedId);
        Optional.ofNullable(update.getValue()).ifPresent(updated::setValue);
        roleDAO.update(updated);
    }
}
