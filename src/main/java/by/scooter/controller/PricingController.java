package by.scooter.controller;

import by.scooter.api.sevice.PricingService;
import by.scooter.dto.event.OrderCreateDTO;
import by.scooter.dto.pricing.ScooterModelPricingDTO;
import by.scooter.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pricing")
@RequiredArgsConstructor
@Validated
public class PricingController {
    private final PricingService pricingService;

    @GetMapping
    public ResponseEntity<List<ScooterModelPricingDTO>> getAll(@RequestParam(required = false) Integer page,
                                                               @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(pricingService.getAll(page, size));
    }

    @GetMapping("/{model_id}")
    public ResponseEntity<ScooterModelPricingDTO> getByModelId(@PathVariable(name = "model_id") Long modelId) {
        return ResponseEntity.ok(pricingService.getByModelId(modelId));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Void> add(@RequestBody @Valid ScooterModelPricingDTO pricing) {
        pricingService.addScooterModelPricing(pricing);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid ScooterModelPricingDTO pricing) {
        pricingService.updateScooterModelPricing(id, pricing);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pricingService.removeScooterModelPricing(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/calculate")
    public ResponseEntity<OrderCreateDTO> calculatePrice(@RequestBody @Valid OrderCreateDTO order,
                                                         BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        order.setPrice(pricingService.calculatePrice(order));
        return ResponseEntity.ok(order);
    }

}
