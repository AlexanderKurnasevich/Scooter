package by.scooter.entity.dto.pricing;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DiscountDTO {

    private Long id;
    private Float discountFactor;
    private String promoCode;
    private LocalDate expireDate;
}
