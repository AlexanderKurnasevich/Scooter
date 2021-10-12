package by.scooter.controller;

import by.scooter.api.sevice.ClientService;
import by.scooter.api.sevice.OrderService;
import by.scooter.entity.dto.event.OrderDTO;
import by.scooter.entity.dto.user.ClientInfoDTO;
import by.scooter.entity.dto.user.ClientUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class ClientController {
    private final ClientService clientService;
    private final OrderService orderService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientInfoDTO> show(@PathVariable Long id) {
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
    public ResponseEntity<Void> add(@RequestBody ClientUserDTO client) {
        clientService.addClient(client);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PutMapping("/clients/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ClientUserDTO client) {
        client.setId(id);
        clientService.updateClient(id, client);
        return ResponseEntity.noContent().build();
    }
}
