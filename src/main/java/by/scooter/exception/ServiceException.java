package by.scooter.exception;

public class ServiceException extends RuntimeException {
    public ServiceException(String reason) {
        super(reason);
    }

    public ServiceException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
