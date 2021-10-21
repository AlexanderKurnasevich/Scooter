package by.scooter.entity.dto.pricing;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.temporal.ChronoUnit;

@Data
public class SubscriptionDTO {

    private Long id;

    @NotNull
    private Long ownerId;

    @NotNull
    private ChronoUnit unit;

    @NotNull
    @Positive
    private Short quantity;
    private Float price;
}
