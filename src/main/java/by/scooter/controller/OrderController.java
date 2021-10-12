package by.scooter.controller;

import by.scooter.api.sevice.OrderService;
import by.scooter.entity.dto.event.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAll(@RequestParam(required = false) Integer page,
                                                 @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(orderService.getAll(page, size));
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody OrderDTO order) {
        orderService.addOrder(order);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> remove(@RequestParam Long id) {
        orderService.removeOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody OrderDTO order) {
        orderService.updateOrder(id, order);
        return ResponseEntity.noContent().build();
    }
}
