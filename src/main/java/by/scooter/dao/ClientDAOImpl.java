package by.scooter.dao;

import by.scooter.api.dao.ClientDAO;
import by.scooter.entity.user.*;
import by.scooter.entity.user.Client_;
import by.scooter.entity.user.Role_;
import by.scooter.entity.user.User_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class ClientDAOImpl extends AbstractDAO<Client> implements ClientDAO {

    @Override
    public Client getByUsername(String username) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = builder.createQuery(getClazz());
        Root<Client> entityRoot = criteriaQuery.from(getClazz());
        criteriaQuery.select(entityRoot)
                .where(builder.equal(entityRoot.get(Client_.USER).get(User_.USERNAME), username));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    protected Class<Client> getClazz() {
        return Client.class;
    }
}
