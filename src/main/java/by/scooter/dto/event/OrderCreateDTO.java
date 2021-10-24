package by.scooter.dto.event;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class OrderCreateDTO {

    private Long id;

    @Future
    private LocalDateTime eventStart;

    @Future
    private LocalDateTime eventEnd;

    @NotNull
    private Long scooterModelId;

    @NotNull
    private Long rentPointId;

    private Float price = 0F;
    private Long scooterId;
    private Long clientId;
    private Integer mileage = 0;

    private Long subscriptionId;
}
