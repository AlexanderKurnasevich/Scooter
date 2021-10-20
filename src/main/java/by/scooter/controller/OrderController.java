package by.scooter.controller;

import by.scooter.api.sevice.OrderService;
import by.scooter.entity.dto.event.OrderCreateDTO;
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

    @GetMapping("/scooter/{id}")
    public ResponseEntity<List<OrderDTO>> getByScooterId(@PathVariable Long id,
                                                         @RequestParam(required = false) Integer page,
                                                         @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(orderService.ordersByScooter(id, page, size));
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping
    public ResponseEntity<Void> add(@RequestBody OrderCreateDTO order) {
        orderService.addOrder(order);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
