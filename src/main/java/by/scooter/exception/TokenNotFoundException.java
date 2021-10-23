package by.scooter.exception;

import javax.persistence.NoResultException;

public class TokenNotFoundException extends DAOException {

    public TokenNotFoundException(String message, NoResultException ex) {
        super(message, ex);
    }
}
