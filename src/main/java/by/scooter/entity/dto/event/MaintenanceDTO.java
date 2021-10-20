package by.scooter.entity.dto.event;

import by.scooter.entity.vehicle.Scooter;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class MaintenanceDTO {

    private Long id;
    private Long userId;

    @FutureOrPresent
    private LocalDateTime eventStart;

    @Future
    private LocalDateTime eventEnd;

    @NotNull
    private Scooter scooter;
}
