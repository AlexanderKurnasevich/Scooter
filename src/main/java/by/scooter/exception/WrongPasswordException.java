package by.scooter.exception;

public class WrongPasswordException extends ServiceException {
    public WrongPasswordException(String reason) {
        super(reason);
    }

    public WrongPasswordException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
