package by.scooter.api.dao;

import by.scooter.entity.user.PasswordResetToken;
import by.scooter.entity.user.User;

public interface PasswordResetTokenDAO extends DAO<PasswordResetToken> {

    PasswordResetToken getByUser(User user);
}
