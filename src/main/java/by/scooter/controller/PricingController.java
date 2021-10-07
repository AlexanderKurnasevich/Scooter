package by.scooter.controller;

import by.scooter.api.sevice.PricingService;
import by.scooter.entity.pricing.RentPointPricing;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pricing")
@RequiredArgsConstructor
public class PricingController {
    private final PricingService pricingService;

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody RentPointPricing pricing) {
        pricingService.addRentPointPricing(pricing);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody RentPointPricing pricing) {
        pricingService.updateRentPointPricing(pricing);
        return ResponseEntity.noContent().build();
    }
}
