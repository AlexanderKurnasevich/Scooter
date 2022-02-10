package by.scooter.service;

import by.scooter.api.dao.PasswordResetTokenDAO;
import by.scooter.api.dao.RoleDAO;
import by.scooter.api.dao.UserDAO;
import by.scooter.api.sevice.UserService;
import by.scooter.api.sevice.UtilService;
import by.scooter.dto.user.ResetPasswordDTO;
import by.scooter.dto.user.RoleDTO;
import by.scooter.dto.user.UserDTO;
import by.scooter.dto.user.UserInfoDTO;
import by.scooter.entity.enumerator.RoleValue;
import by.scooter.entity.user.PasswordResetToken;
import by.scooter.entity.user.Role;
import by.scooter.entity.user.User;
import by.scooter.exception.WrongPasswordException;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final ModelMapper mapper;
    private final UtilService utilService;
    private final PasswordResetTokenDAO resetTokenDAO;
    private final PlatformTransactionManager txManager;
    private PasswordEncoder passwordEncoder;

    @Value("${database.admin.password}")
    private String password;
    @Value("${database.admin.email}")
    private String email;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, ModelMapper mapper, UtilService utilService,
                           PasswordResetTokenDAO resetTokenDAO, PlatformTransactionManager txManager) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.mapper = mapper;
        this.utilService = utilService;
        this.resetTokenDAO = resetTokenDAO;
        this.txManager = txManager;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    protected void adminInit() {
        TransactionTemplate tmpl = new TransactionTemplate(txManager);
        tmpl.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    userDAO.findByLogin("admin");
                } catch (UsernameNotFoundException ignore) {
                    Role role = roleDAO.getByRole(RoleValue.ROLE_ADMIN);
                    String pass = passwordEncoder.encode(password);
                    User admin = new User("admin", pass, email, new HashSet<>(List.of(role)));
                    userDAO.save(admin);
                }
            }
        });
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
    @Transactional
    public void remove(Long id) {
        userDAO.delete(id);
    }

    @Override
    public UserInfoDTO getById(Long id) {
        return mapper.map(userDAO.getById(id), UserInfoDTO.class);
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
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userDAO.findByLogin(login);
    }

    @Override
    public UserInfoDTO getAuthorizedUser() throws UsernameNotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
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
        UserInfoDTO auth = getAuthorizedUser();
        if (!Objects.equals(auth.getId(), id)) {
            log.info("Unauthorized access attempt to user id={} by id={}", id, auth.getId());
            throw new AccessDeniedException("Current user isn't owner");
        }
        return checked;
    }

    @Override
    @Transactional
    public void setNewPassword(ResetPasswordDTO dto) {
        PasswordResetToken token = resetTokenDAO.getByUser(userDAO.findByEmail(dto.getEmail()));
        if (dto.getToken().equals(token.getToken()) && LocalDateTime.now().isBefore(token.getExpiryDate())) {
            User user = userDAO.findByEmail(dto.getEmail());
            user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            userDAO.update(user);
        }
    }
}
