package by.scooter.api.sevice;

public interface PasswordResetService {

    void generateResetToken(String email);
}
