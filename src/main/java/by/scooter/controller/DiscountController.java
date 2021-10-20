package by.scooter.controller;

import by.scooter.api.sevice.DiscountService;
import by.scooter.entity.dto.event.OrderCreateDTO;
import by.scooter.entity.dto.pricing.DiscountDTO;
import by.scooter.entity.dto.pricing.ScooterModelPricingDTO;
import by.scooter.entity.pricing.Discount;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountService discountService;

    @GetMapping
    public ResponseEntity<List<DiscountDTO>> getAll(@RequestParam(required = false) Integer page,
                                                    @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(discountService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(discountService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody DiscountDTO discount) {
        discountService.add(discount);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody DiscountDTO discount) {
        discountService.update(id, discount);
        return ResponseEntity.noContent().build();
    }
}
