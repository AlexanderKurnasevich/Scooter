package by.scooter.entity.dto.event;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class OrderDTO {

    @NotNull
    private Long id;

    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;
    private Float price;

    @NotNull
    private Long scooterId;

    @NotNull
    private Long clientId;

    @NotNull
    private Integer mileage; //in meters
}
