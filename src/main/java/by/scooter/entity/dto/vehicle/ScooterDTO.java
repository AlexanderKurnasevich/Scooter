package by.scooter.entity.dto.vehicle;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode
public class ScooterDTO {

    private Long id;
    private Long modelId;
    private Long currentPointId;
    private Integer odometer; //in meters
}
