package by.scooter.controller;

import by.scooter.api.sevice.OrderService;
import by.scooter.dto.event.OrderCreateDTO;
import by.scooter.dto.event.OrderDTO;
import by.scooter.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Void> add(@RequestBody @Valid OrderCreateDTO order, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        orderService.addOrder(order);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping
    public ResponseEntity<Void> remove(@RequestParam Long id) {
        orderService.removeOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody @Valid OrderDTO order, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        orderService.updateOrder(id, order);
        return ResponseEntity.noContent().build();
    }
}
