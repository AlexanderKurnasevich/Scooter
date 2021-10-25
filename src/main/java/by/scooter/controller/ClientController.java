package by.scooter.controller;

import by.scooter.api.sevice.ClientService;
import by.scooter.api.sevice.OrderService;
import by.scooter.dto.event.OrderDTO;
import by.scooter.dto.user.ClientInfoDTO;
import by.scooter.dto.user.ClientUserDTO;
import by.scooter.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class ClientController {
    private final ClientService clientService;
    private final OrderService orderService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientInfoDTO> show(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_CLIENT"))) {
            clientService.checkOwner(id);
        }
        return ResponseEntity.ok(clientService.getById(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/clients")
    public ResponseEntity<List<ClientInfoDTO>> getAll(@RequestParam(required = false) Integer page,
                                                      @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(clientService.getAll(page, size));
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping("/clients/{id}/history")
    public ResponseEntity<List<OrderDTO>> clientOrders(@PathVariable Long id,
                                                       @RequestParam(required = false) Integer page,
                                                       @RequestParam(required = false) Integer size) {
        clientService.checkOwner(id);
        return ResponseEntity.ok(orderService.ordersByClient(id, page, size));
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> add(@RequestBody @Valid ClientUserDTO client, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        clientService.addClient(client);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PutMapping("/clients/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody @Valid ClientUserDTO client, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        client.setId(id);
        clientService.updateClient(id, client);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PutMapping("/orders/finish")
    public ResponseEntity<Void> finishOrder(@RequestBody @Valid OrderDTO order, BindingResult result,
                                            @RequestParam Long rentPointId) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        orderService.handleOrder(order, rentPointId);
        return ResponseEntity.noContent().build();
    }
}
