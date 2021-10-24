package by.scooter.dto.vehicle;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode
public class ScooterDTO {

    private Long id;

    @NotNull
    private Long modelId;

    @NotNull
    private Long currentPointId;

    private Integer odometer = 0; //in meters
}
