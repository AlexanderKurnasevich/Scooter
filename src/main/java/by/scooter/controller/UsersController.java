package by.scooter.controller;

import by.scooter.api.sevice.PasswordResetService;
import by.scooter.api.sevice.UserService;
import by.scooter.dto.user.ResetPasswordDTO;
import by.scooter.dto.user.UserDTO;
import by.scooter.dto.user.UserInfoDTO;
import by.scooter.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UsersController {

    private final UserService userService;
    private final PasswordResetService resetService;

    @Autowired
    public UsersController(UserService userService, PasswordResetService resetService) {
        this.userService = userService;
        this.resetService = resetService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/adduser")
    public ResponseEntity<Void> addUser(@RequestBody @Valid UserDTO user, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        userService.save(user);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_CLIENT')")
    @GetMapping("/users/{id}")
    public ResponseEntity<UserInfoDTO> findUserById(@PathVariable Long id) {
        userService.checkOwner(id);
        return ResponseEntity.ok(userService.getById(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @PutMapping("/users/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id,
                                           @RequestBody @Valid UserDTO user, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_CLIENT"))) {
            userService.checkOwner(id);
        }

        user.setId(id);
        userService.update(user);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reset_request")
    public ResponseEntity<Void> resetPasswordInit(@RequestParam String email) {
        resetService.generateResetToken(email);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/password_reset")
    public ResponseEntity<Void> resetPassword(@RequestBody @Valid ResetPasswordDTO body, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        userService.setNewPassword(body);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<UserInfoDTO>> getUsers(@RequestParam(required = false) Integer page,
                                                      @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(userService.getAll(page, size));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        userService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
