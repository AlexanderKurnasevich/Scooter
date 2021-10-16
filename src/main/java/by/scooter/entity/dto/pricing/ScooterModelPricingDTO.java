package by.scooter.entity.dto.pricing;

import lombok.Data;

@Data
public class ScooterModelPricingDTO {

    private Long id;
    private Long scooterModelId;
    private Float minutePrice;
    private Float hourPrice;
    private Float dayPrice;

}