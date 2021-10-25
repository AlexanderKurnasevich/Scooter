package by.scooter.dto.pricing;

import by.scooter.entity.OnUpdate;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
public class DiscountDTO {

    private Long id;

    @NotNull
    @Positive(groups = {OnUpdate.class})
    private Float discountFactor;

    @NotNull
    private String promoCode;

    @Future(groups = {OnUpdate.class})
    private LocalDate expireDate;
}
