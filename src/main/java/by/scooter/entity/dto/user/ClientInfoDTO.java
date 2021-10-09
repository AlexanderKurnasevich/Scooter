package by.scooter.entity.dto.user;

import lombok.Data;

@Data
public class ClientInfoDTO {

    private Long id;
    private String username;
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
}
