package by.scooter.entity.user;

import by.scooter.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends AbstractEntity {

    @Size(min = 1, max = 30, message = "Имя не короче 2 и не длинее 30 символов")
    @Pattern(regexp = "^[A-Za-zА-Яа-я]*$", message = "Только буквы")
    private String firstName;

    @Size(min = 1, max = 40, message = "Фамилия не короче 2 и не длинее 40 символов")
    @Pattern(regexp = "^[A-Za-zА-Яа-я]*$", message = "Только буквы")
    private String lastName;

    @Email(message = "Адрес введён неверно")
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
