package by.scooter.service;

import by.scooter.api.dao.RoleDAO;
import by.scooter.api.sevice.RoleService;
import by.scooter.entity.enumerator.RoleValue;
import by.scooter.entity.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

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
        update.setId(updatedId);
        roleDAO.update(update);
    }
}
