package by.scooter.entity.user;

import by.scooter.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetToken extends AbstractEntity {

    @Column(nullable = false)
    @NotNull
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = true)
    private User user;

    @Column(nullable = false, name = "expiry_date")
    private LocalDateTime expiryDate = LocalDateTime.now().plusHours(12);
}
