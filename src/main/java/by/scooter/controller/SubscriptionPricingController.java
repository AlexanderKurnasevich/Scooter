package by.scooter.controller;

import by.scooter.api.sevice.SubscriptionPricingService;
import by.scooter.dto.pricing.SubscriptionPricingDTO;
import by.scooter.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pricing/subscription")
public class SubscriptionPricingController {

    private final SubscriptionPricingService service;

    @Autowired
    public SubscriptionPricingController(SubscriptionPricingService service) {
        this.service = service;
    }

    @GetMapping("/unit")
    public ResponseEntity<SubscriptionPricingDTO> getByUnit(@RequestParam String unit) {
        return ResponseEntity.ok(service.getByUnit(unit));
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionPricingDTO>> getAll(@RequestParam(required = false) Integer page,
                                                               @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody @Valid SubscriptionPricingDTO pricing, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        service.addSubscriptionPricing(pricing);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody @Valid SubscriptionPricingDTO pricing, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        service.updateSubscriptionPricing(id, pricing);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.removeSubscriptionPricing(id);
        return ResponseEntity.noContent().build();
    }
}
