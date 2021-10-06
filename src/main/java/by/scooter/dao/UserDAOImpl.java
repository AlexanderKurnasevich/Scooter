package by.scooter.dao;

import by.scooter.api.dao.UserDAO;
import by.scooter.entity.user.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {
    @Override
    protected Class<User> getClazz() {
        return User.class;
    }
}
