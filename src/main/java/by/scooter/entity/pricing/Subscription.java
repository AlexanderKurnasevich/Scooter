package by.scooter.entity.pricing;

import by.scooter.entity.AbstractEntity;
import by.scooter.entity.user.Client;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "subscriptions")
public class Subscription extends AbstractEntity {

    @ManyToOne
    private Client owner;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ChronoUnit unit;

    @Column(nullable = false)
    @Positive
    private Integer quantity;

    @Column(nullable = false, name = "expiry_day")
    @NotNull
    private LocalDate expiryDay;

    @Column(nullable = false)
    @PositiveOrZero
    private Float price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscription)) return false;
        if (!super.equals(o)) return false;
        Subscription that = (Subscription) o;
        return getOwner().equals(that.getOwner()) && getUnit() == that.getUnit() && getQuantity().equals(that.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getOwner(), getUnit(), getQuantity());
    }
}
