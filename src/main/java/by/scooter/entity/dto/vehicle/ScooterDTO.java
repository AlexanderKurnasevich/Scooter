package by.scooter.entity.dto.vehicle;

import lombok.Data;

@Data
public class ScooterDTO {

    private Long id;
    private Long modelId;
    private Long homePointId;
    private Float chargePercent;
    private Integer odometer; //in meters
}
