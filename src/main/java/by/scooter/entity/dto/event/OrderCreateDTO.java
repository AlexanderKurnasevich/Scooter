package by.scooter.entity.dto.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderCreateDTO {

    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;
    private Float price;
    private Long scooterId;
    private Long scooterModelId;
    private Long clientId;
    private Long rentPointId;
}
