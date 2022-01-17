package by.scooter.entity.pricing;

import by.scooter.entity.vehicle.ScooterModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@Table(name = "scooter_model_pricing")
@Entity
public class ScooterModelPricing extends PricingStrategy {

    @OneToOne
    @JoinColumn(nullable = false, name = "scooter_model_id")
    private ScooterModel scooterModel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScooterModelPricing)) return false;
        if (!super.equals(o)) return false;
        ScooterModelPricing that = (ScooterModelPricing) o;
        return getScooterModel().equals(that.getScooterModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getScooterModel());
    }
}