package by.scooter.entity.pricing;

import by.scooter.entity.vehicle.ScooterModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "scooter_model_pricing")
@Entity
public class ScooterModelPricing extends PricingStrategy {

    @OneToOne
    @JoinColumn(nullable = false)
    private ScooterModel scooterModel;

}