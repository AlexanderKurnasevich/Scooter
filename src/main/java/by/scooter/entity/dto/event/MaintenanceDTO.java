package by.scooter.entity.dto.event;

import by.scooter.entity.vehicle.Scooter;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MaintenanceDTO {

    private Long id;
    private Long userId;
    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;
    private Scooter scooter;
}
