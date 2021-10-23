package by.scooter.exception;

import javax.persistence.NoResultException;

public class UserNotFoundException extends DAOException {

    public UserNotFoundException(String message, NoResultException ex) {
        super(message, ex);
    }
}
