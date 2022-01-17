package by.scooter.entity.pricing;

import by.scooter.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
public abstract class PricingStrategy extends AbstractEntity {

    @Column(nullable = false, name = "minute_price")
    protected Float minutePrice;

    @Column(nullable = false, name = "hour_price")
    protected Float hourPrice;

    @Column(nullable = false, name = "day_price")
    protected Float dayPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PricingStrategy)) return false;
        if (!super.equals(o)) return false;
        PricingStrategy that = (PricingStrategy) o;
        return getMinutePrice().equals(that.getMinutePrice()) && getHourPrice().equals(that.getHourPrice()) && getDayPrice().equals(that.getDayPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMinutePrice(), getHourPrice(), getDayPrice());
    }
}
