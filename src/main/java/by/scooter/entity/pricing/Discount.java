package by.scooter.entity.pricing;

import by.scooter.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "discounts")
public class Discount extends AbstractEntity {

    @Column(nullable = false, columnDefinition = "float default 0.0")
    private Float discountFactor;

    @Column(nullable = false, unique = true)
    private String promoCode;
}
