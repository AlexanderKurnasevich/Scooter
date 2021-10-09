package by.scooter.controller;

import by.scooter.api.sevice.PricingService;
import by.scooter.entity.dto.pricing.RentPointPricingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pricing")
@RequiredArgsConstructor
public class PricingController {
    private final PricingService pricingService;

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody RentPointPricingDTO pricing) {
        pricingService.addRentPointPricing(pricing);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody RentPointPricingDTO pricing) {
        pricingService.updateRentPointPricing(pricing);
        return ResponseEntity.noContent().build();
    }
}
