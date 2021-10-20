package by.scooter.controller;

import by.scooter.api.sevice.PricingService;
import by.scooter.entity.dto.event.OrderCreateDTO;
import by.scooter.entity.dto.pricing.ScooterModelPricingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pricing")
@RequiredArgsConstructor
public class PricingController {
    private final PricingService pricingService;

    @GetMapping
    public ResponseEntity<List<ScooterModelPricingDTO>> getAll(@RequestParam(required = false) Integer page,
                                                               @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(pricingService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScooterModelPricingDTO> getScooterById(@PathVariable Long id) {
        return ResponseEntity.ok(pricingService.getByModelId(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Void> add(@RequestBody ScooterModelPricingDTO pricing) {
        pricingService.addScooterModelPricing(pricing);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ScooterModelPricingDTO pricing) {
        pricingService.updateScooterModelPricing(id, pricing);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/calculate")
    public ResponseEntity<OrderCreateDTO> calculatePrice(@RequestBody OrderCreateDTO order) {
        order.setPrice(pricingService.calculatePrice(order, null));
        return ResponseEntity.ok(order);
    }

}
