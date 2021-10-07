package by.scooter.service;

import by.scooter.api.dao.RoleDAO;
import by.scooter.api.dao.UserDAO;
import by.scooter.api.sevice.UserService;
import by.scooter.api.sevice.UtilService;
import by.scooter.entity.dto.UserDTO;
import by.scooter.entity.dto.UserInfoDTO;
import by.scooter.entity.user.Role;
import by.scooter.entity.user.User;
import by.scooter.exception.WrongPasswordException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final ModelMapper mapper;
    private final UtilService utilService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void save(UserDTO user) {
        loadRoleIdAndEncodePassword(user);
        userDAO.save(mapper.map(user, User.class));
    }

    @Override
    @Transactional
    public void update(UserDTO user) {
        loadRoleIdAndEncodePassword(user);
        userDAO.update(mapper.map(user, User.class));
    }

    private void loadRoleIdAndEncodePassword(UserDTO user) {
        Set<Role> roleSet = new HashSet<>();
        for (Role role : user.getRoles()) {
            roleSet.add(roleDAO.getByRole(role.getValue()));
        }
        user.setRoles(roleSet);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    @Override
    public UserInfoDTO getById(Long id) {
        return mapper.map(userDAO.getById(id), UserInfoDTO.class);
    }

    @Override
    public UserInfoDTO logIn(String login, CharSequence password) {
        UserDTO userDTO = mapper.map(userDAO.findByLogin(login), UserDTO.class);
        if (passwordEncoder.matches(password, userDTO.getPassword())) {
            return mapper.map(userDTO, UserInfoDTO.class);
        }
        log.info("Wrong password for: {}", login);
        throw new WrongPasswordException("Passwords don't match");
    }

    @Override
    public List<UserInfoDTO> getAll() {
        return utilService.convertList(userDAO.getAll(), UserInfoDTO.class);
    }

    @Override
    public List<UserInfoDTO> getAll(Integer page, Integer size) {
        return utilService.convertList(userDAO.getAll(page, size), UserInfoDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userDAO.findByLogin(login);
    }

    @Override
    public UserInfoDTO getAuthorizedUser() throws UsernameNotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return mapper.map(loadUserByUsername(username), UserInfoDTO.class);
    }
}
