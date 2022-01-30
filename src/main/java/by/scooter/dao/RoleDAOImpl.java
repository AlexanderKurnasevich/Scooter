package by.scooter.dao;

import by.scooter.api.dao.RoleDAO;
import by.scooter.entity.enumerator.RoleValue;
import by.scooter.entity.user.Role;
import by.scooter.entity.user.Role_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class RoleDAOImpl extends AbstractDAO<Role> implements RoleDAO {

    @Override
    public Role getByRole(RoleValue value) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = builder.createQuery(getClazz());
        Root<Role> entityRoot = criteriaQuery.from(getClazz());
        criteriaQuery.select(entityRoot).where(builder.equal(entityRoot.get(Role_.VALUE), value));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    protected Class<Role> getClazz() {
        return Role.class;
    }
}
