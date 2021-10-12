package by.scooter.service;

import by.scooter.api.dao.RoleDAO;
import by.scooter.api.dao.UserDAO;
import by.scooter.api.sevice.UserService;
import by.scooter.api.sevice.UtilService;
import by.scooter.entity.dto.user.RoleDTO;
import by.scooter.entity.dto.user.UserDTO;
import by.scooter.entity.dto.user.UserInfoDTO;
import by.scooter.entity.user.Client;
import by.scooter.entity.user.Role;
import by.scooter.entity.user.User;
import by.scooter.exception.WrongPasswordException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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
        userDAO.save(loadRoleIdAndEncodePassword(user));
    }

    @Override
    @Transactional
    public void update(UserDTO user) {
        userDAO.update(loadRoleIdAndEncodePassword(user));
    }

    @Override
    @Transactional
    public void remove(Long id) {
        userDAO.delete(id);
    }

    private User loadRoleIdAndEncodePassword(UserDTO user) {
        User target = mapper.map(user, User.class);
        Set<Role> roleSet = new HashSet<>();
        for (RoleDTO role : user.getRoles()) {
            roleSet.add(roleDAO.getByRole(role.getValue()));
        }
        target.setRoles(roleSet);
        target.setPassword(passwordEncoder.encode(user.getPassword()));
        return target;
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

    @Override
    public User checkOwner(Long id) throws AccessDeniedException {
        User checked = userDAO.getById(id);
        if (!Objects.equals(getAuthorizedUser().getId(), id)) {
            throw new AccessDeniedException("Current user isn't owner");
        }
        return checked;
    }
}
