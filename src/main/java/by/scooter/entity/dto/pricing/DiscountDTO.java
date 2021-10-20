package by.scooter.entity.dto.pricing;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
public class DiscountDTO {

    private Long id;

    @NotNull
    @Positive
    private Float discountFactor;

    @NotNull
    private String promoCode;

    private LocalDate expireDate;
}
