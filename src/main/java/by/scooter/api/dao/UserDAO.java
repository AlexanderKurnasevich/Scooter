package by.scooter.api.dao;

import by.scooter.entity.user.User;

public interface UserDAO extends DAO<User> {

    User findByLogin(String login);

    User findByEmail(String email);
}
