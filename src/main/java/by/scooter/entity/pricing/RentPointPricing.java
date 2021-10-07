package by.scooter.entity.pricing;

import by.scooter.entity.AbstractEntity;
import by.scooter.entity.location.RentPoint;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Table(name = "rent_point_pricing")
@Entity
public class RentPointPricing extends AbstractEntity {

    @OneToOne
    @JoinColumn(nullable = false)
    private RentPoint rentPoint;

    @Column(columnDefinition = "float default 1")
    private Float commonFactor;

    @Column(columnDefinition = "float default 1")
    private Float minuteFactor;

    @Column(columnDefinition = "float default 1")
    private Float hourFactor;

    @Column(columnDefinition = "float default 1")
    private Float dayFactor;

    @Column(columnDefinition = "float default 1")
    private Float weekFactor;

    @Column(columnDefinition = "float default 1")
    private Float monthFactor;

    @OneToMany
    private Set<SpecialModelPricing> strategiesOverride;

}