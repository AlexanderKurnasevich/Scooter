package by.scooter.entity.pricing;

import by.scooter.entity.location.RentPoint;
import by.scooter.entity.vehicle.ScooterModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "special_pricing")
@Entity
public class SpecialModelPricing extends PricingStrategy {

    @ManyToOne
    private RentPoint rentPoint;

    @ManyToOne
    private ScooterModel scooterModel;
}
