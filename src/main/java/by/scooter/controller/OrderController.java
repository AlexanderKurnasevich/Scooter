package by.scooter.controller;

import by.scooter.api.sevice.OrderService;
import by.scooter.entity.event.Order;
import by.scooter.entity.vehicle.ScooterModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAll(@RequestParam(required = false) Integer page,
                                              @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(orderService.getAll(page, size));
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody Order order) {
        orderService.addOrder(order);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> remove(@RequestParam Long id) {
        orderService.removeOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Order order) {
        orderService.updateOrder(id, order);
        return ResponseEntity.noContent().build();
    }
}
