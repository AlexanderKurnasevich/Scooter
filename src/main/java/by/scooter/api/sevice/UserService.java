package by.scooter.api.sevice;

import by.scooter.entity.dto.UserDTO;
import by.scooter.entity.dto.UserInfoDTO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
    void save(UserDTO user);

    void update(UserDTO user);

    UserInfoDTO getById(Long id);

    UserInfoDTO logIn(String login, CharSequence password);

    UserInfoDTO getAuthorizedUser() throws UsernameNotFoundException;

    List<UserInfoDTO> getAll();

    List<UserInfoDTO> getAll(Integer page, Integer size);
}
