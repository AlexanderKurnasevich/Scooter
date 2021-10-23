package by.scooter.entity.pricing;

import by.scooter.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "discounts")
public class Discount extends AbstractEntity {

    @Column(nullable = false, columnDefinition = "float default 1.0")
    @Positive
    private Float discountFactor;

    @Column(nullable = false, unique = true)
    private String promoCode;

    @Column
    private LocalDate expireDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discount)) return false;
        if (!super.equals(o)) return false;
        Discount discount = (Discount) o;
        return getDiscountFactor().equals(discount.getDiscountFactor()) && getPromoCode().equals(discount.getPromoCode()) && Objects.equals(getExpireDate(), discount.getExpireDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDiscountFactor(), getPromoCode(), getExpireDate());
    }
}
