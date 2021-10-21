package by.scooter.entity.pricing;

import by.scooter.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "subscription_pricing")
public class SubscriptionPricing extends AbstractEntity {

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ChronoUnit unit;

    @Column(nullable = false)
    @Positive
    private Float price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubscriptionPricing)) return false;
        if (!super.equals(o)) return false;
        SubscriptionPricing that = (SubscriptionPricing) o;
        return unit == that.unit && price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), unit, price);
    }
}
