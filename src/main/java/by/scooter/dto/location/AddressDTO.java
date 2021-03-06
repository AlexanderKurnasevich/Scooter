package by.scooter.dto.location;

import lombok.*;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AddressDTO {

    private Long id;
    private String country;
    private String city;
    private String street;
    private Short number;
    private String postfix;
}
