package by.scooter.entity.dto.pricing;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ScooterModelPricingDTO {

    private Long id;

    @NotNull
    private Long scooterModelId;

    @NotNull
    private Float minutePrice;

    @NotNull
    private Float hourPrice;

    @NotNull
    private Float dayPrice;

}