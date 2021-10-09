package by.scooter.entity.dto.user;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ClientUserDTO {

    private Long id;

    @Pattern(regexp = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$")
    @NotNull
    private String username;

    private Long userId;

    @Min(value = 4)
    private String password;

    @Size(min = 1, max = 30, message = "Имя не короче 2 и не длинее 30 символов")
    @Pattern(regexp = "^[A-Za-zА-Яа-я]*$", message = "Только буквы")
    @NotNull
    private String firstName;

    @Size(min = 1, max = 40, message = "Фамилия не короче 2 и не длинее 40 символов")
    @Pattern(regexp = "^[A-Za-zА-Яа-я]*$", message = "Только буквы")
    @NotNull
    private String lastName;

    @Email(message = "Адрес введён неверно")
    @NotNull
    private String email;
}
