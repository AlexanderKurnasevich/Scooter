package by.scooter.entity.dto.vehicle;

import lombok.Data;

@Data
public class ScooterFilterDTO {

    private Long modelId;
    private Long rentPoint;
    private String sortedColumn;
}
