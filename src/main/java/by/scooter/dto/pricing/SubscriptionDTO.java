package by.scooter.dto.pricing;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
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
    private Integer quantity;

    @NotNull
    @Future
    private LocalDate expiryDay;

    private Float price;
}
