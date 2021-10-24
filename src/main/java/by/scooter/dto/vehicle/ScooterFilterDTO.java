package by.scooter.dto.vehicle;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScooterFilterDTO {

    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;
    private Long scooterModelId;
    private Long rentPointId;
    private String sortedColumn;
}
