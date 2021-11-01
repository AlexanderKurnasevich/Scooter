package by.scooter.config;

import by.scooter.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<Map<String, String>> handleWrongPasswordException(ValidationException ex) {
        return new ResponseEntity<>(ex.getFieldMessageMap(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SubscriptionException.class)
    public ResponseEntity<String> handleSubscriptionException(SubscriptionException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
        return new ResponseEntity<>(ex.getFieldMessageMap(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DAOException.class)
    public ResponseEntity<String> handleDAOException(DAOException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleEntityNotFoundException(AccessDeniedException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DataConsistencyException.class)
    public ResponseEntity<String> handleDataConsistencyException(DataConsistencyException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
