package by.scooter.controller;

import by.scooter.api.sevice.DiscountService;
import by.scooter.dto.pricing.DiscountDTO;
import by.scooter.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Void> add(@RequestBody @Valid DiscountDTO discount, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        discountService.add(discount);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody @Valid DiscountDTO discount, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        discountService.update(id, discount);
        return ResponseEntity.noContent().build();
    }
}
