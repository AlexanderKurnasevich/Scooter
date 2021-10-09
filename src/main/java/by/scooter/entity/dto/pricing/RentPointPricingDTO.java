package by.scooter.entity.dto.pricing;

import lombok.Data;

import java.util.Set;

@Data
public class RentPointPricingDTO {

    private Long id;
    private Long rentPointId;
    private Float commonFactor;
    private Float minuteFactor;
    private Float hourFactor;
    private Float dayFactor;
    private Float weekFactor;
    private Float monthFactor;
    private Set<SpecialModelPricingDTO> strategiesOverride;
}