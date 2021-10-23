package by.scooter.dao;

import by.scooter.api.dao.PasswordResetTokenDAO;
import by.scooter.entity.user.PasswordResetToken;
import by.scooter.entity.user.PasswordResetToken_;
import by.scooter.entity.user.User;
import by.scooter.exception.TokenNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
@Log4j2
public class PasswordResetTokenDAOImpl extends AbstractDAO<PasswordResetToken> implements PasswordResetTokenDAO {

    @Override
    public PasswordResetToken getByUser(User user) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PasswordResetToken> criteriaQuery = builder.createQuery(getClazz());
        Root<PasswordResetToken> entityRoot = criteriaQuery.from(getClazz());
        criteriaQuery.select(entityRoot).where(builder.equal(entityRoot.get(PasswordResetToken_.USER), user));
        try {
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException ex) {
            log.info("Token for {} not found", user);
            throw new TokenNotFoundException("Token not found", ex);
        }
    }

    @Override
    protected Class<PasswordResetToken> getClazz() {
        return PasswordResetToken.class;
    }
}
