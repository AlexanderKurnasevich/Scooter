package by.scooter.entity.pricing;

import by.scooter.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class PricingStrategy extends AbstractEntity {

    @Column(nullable = false)
    protected Float minutePrice;

    @Column(nullable = false)
    protected Float hourPrice;

    @Column(nullable = false)
    protected Float dayPrice;

    @Column(nullable = false)
    protected Float weekPrice;

    @Column(nullable = false)
    protected Float monthPrice;
}
