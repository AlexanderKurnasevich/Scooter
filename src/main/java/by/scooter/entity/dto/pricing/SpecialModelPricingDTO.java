package by.scooter.entity.dto.pricing;

import lombok.Data;

@Data
public class SpecialModelPricingDTO {

    private Long id;
    private Long rentPointId;
    private Long scooterModelId;
    private Float minutePrice;
    private Float hourPrice;
    private Float dayPrice;
    private Float weekPrice;
    private Float monthPrice;
}
