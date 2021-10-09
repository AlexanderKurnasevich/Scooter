package by.scooter.entity.dto.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {

    private Long id;
    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;
    private Long scooterId;
    private Long clientId;
    private Integer mileage; //in meters
}
