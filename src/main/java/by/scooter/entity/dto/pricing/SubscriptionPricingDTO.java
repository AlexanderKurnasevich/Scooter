package by.scooter.entity.dto.pricing;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.temporal.ChronoUnit;

@Data
public class SubscriptionPricingDTO {

    private Long id;

    @NotNull
    private ChronoUnit unit;

    @NotNull
    @Positive
    private Float price;
}
