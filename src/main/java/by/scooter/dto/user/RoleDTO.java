package by.scooter.dto.user;

import by.scooter.entity.enumerator.RoleValue;
import lombok.Data;

@Data
public class RoleDTO {

    private Long id;
    private RoleValue value;
}
