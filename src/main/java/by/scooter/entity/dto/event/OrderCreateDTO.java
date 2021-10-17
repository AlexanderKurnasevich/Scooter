package by.scooter.entity.dto.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderCreateDTO {

    private Long id;
    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;
    private Long scooterModelId;
    private Long rentPointId;
    private Float price = 0F;
    private Long scooterId;
    private Long clientId;
    private Integer mileage = 0;
}
