package by.scooter.api.sevice;

import by.scooter.dto.user.UserDTO;
import by.scooter.dto.user.UserInfoDTO;
import by.scooter.dto.user.ResetPasswordDTO;
import by.scooter.entity.user.User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {

    void save(UserDTO user);

    void update(UserDTO user);

    void remove(Long id);

    UserInfoDTO getById(Long id);

    UserInfoDTO logIn(UserDTO userDTO);

    UserInfoDTO getAuthorizedUser() throws UsernameNotFoundException;

    List<UserInfoDTO> getAll();

    List<UserInfoDTO> getAll(Integer page, Integer size);

    User checkOwner(Long id) throws AccessDeniedException;

    void setNewPassword(ResetPasswordDTO dto);
}
