package by.scooter.entity.user;

import by.scooter.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends AbstractEntity {

    @Size(min = 1, max = 30, message = "Имя не короче 2 и не длинее 30 символов")
    @Pattern(regexp = "^[A-Za-zА-Яа-я]*$", message = "Только буквы")
    @NotNull
    private String firstName;

    @Size(min = 1, max = 40, message = "Фамилия не короче 2 и не длинее 40 символов")
    @Pattern(regexp = "^[A-Za-zА-Яа-я]*$", message = "Только буквы")
    @NotNull
    private String lastName;

    @Email(message = "Адрес введён неверно")
    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private User user;
}
