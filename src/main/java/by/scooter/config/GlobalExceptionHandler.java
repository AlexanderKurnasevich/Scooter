package by.scooter.config;

import by.scooter.exception.DAOException;
import by.scooter.exception.DataConsistencyException;
import by.scooter.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

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

    @ExceptionHandler(DataConsistencyException.class)
    public ResponseEntity<String> handleDataConsistencyException(DataConsistencyException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
