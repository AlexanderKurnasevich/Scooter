package by.scooter.dto.event;

import by.scooter.entity.OnUpdate;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class OrderDTO {

    @NotNull(groups = {OnUpdate.class})
    private Long id;

    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;
    private Float price;

    @NotNull
    private Long scooterId;

    @NotNull
    private Long clientId;

    @NotNull(groups = {OnUpdate.class})
    private Integer mileage; //in meters
}
