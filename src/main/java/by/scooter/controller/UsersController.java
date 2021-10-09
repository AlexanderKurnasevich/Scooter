package by.scooter.controller;

import by.scooter.api.sevice.UserService;
import by.scooter.entity.dto.user.UserDTO;
import by.scooter.entity.dto.user.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/adduser")
    public ResponseEntity<Void> addUser(@RequestBody UserDTO user) {
        userService.save(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserInfoDTO> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserDTO user) {
        if (!Objects.equals(userService.getAuthorizedUser().getId(), id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        user.setId(id);
        userService.update(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserInfoDTO>> getUsers(@RequestParam(required = false) Integer page,
                                                      @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(userService.getAll(page, size));
    }

    @DeleteMapping("/users")
    public ResponseEntity<Void> remove(@RequestParam Long id) {
        userService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
