package by.scooter.controller;

import by.scooter.api.sevice.UserService;
import by.scooter.entity.dto.user.UserDTO;
import by.scooter.entity.dto.user.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping("/login")
    public ResponseEntity<UserInfoDTO> logIn(@RequestParam String login,
                                             @RequestParam CharSequence password) {
        return ResponseEntity.ok(userService.logIn(login, password));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/adduser")
    public ResponseEntity<Void> addUser(@RequestBody UserDTO user) {
        userService.save(user);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_CLIENT')")
    @GetMapping("/users/{id}")
    public ResponseEntity<UserInfoDTO> findUserById(@PathVariable Long id) {
        userService.checkOwner(id);
        return ResponseEntity.ok(userService.getById(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_CLIENT')")
    @PutMapping("/users/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserDTO user) {
        if (!Objects.equals(userService.getAuthorizedUser().getId(), id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        user.setId(id);
        userService.update(user);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<UserInfoDTO>> getUsers(@RequestParam(required = false) Integer page,
                                                      @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(userService.getAll(page, size));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/users")
    public ResponseEntity<Void> remove(@RequestParam Long id) {
        userService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
