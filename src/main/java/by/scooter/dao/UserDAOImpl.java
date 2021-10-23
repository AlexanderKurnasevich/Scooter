package by.scooter.dao;

import by.scooter.api.dao.UserDAO;
import by.scooter.entity.user.User;
import by.scooter.entity.user.User_;
import by.scooter.exception.UserNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
@Log4j2
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {

    @Override
    public User findByLogin(String login) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> entityRoot = criteriaQuery.from(getClazz());
        criteriaQuery.select(entityRoot).where(builder.equal(entityRoot.get(User_.USERNAME), login));
        try {
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException ex) {
            log.info("User with username: {} not found", login);
            throw new UsernameNotFoundException("User not found", ex);
        }
    }

    @Override
    public User findByEmail(String email) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> entityRoot = criteriaQuery.from(getClazz());
        criteriaQuery.select(entityRoot).where(builder.equal(entityRoot.get(User_.EMAIL), email));
        try {
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException ex) {
            log.info("User with email: {} not found", email);
            throw new UserNotFoundException("User not found", ex);
        }
    }

    @Override
    protected Class<User> getClazz() {
        return User.class;
    }
}
