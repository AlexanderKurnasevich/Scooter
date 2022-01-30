package by.scooter.controller;

import by.scooter.api.sevice.RentPointService;
import by.scooter.dto.location.RentPointDTO;
import by.scooter.dto.location.RentPointFilterDTO;
import by.scooter.dto.vehicle.ScooterDTO;
import by.scooter.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/points")
public class RentPointController {

    private final RentPointService rentPointService;

    @Autowired
    public RentPointController(RentPointService rentPointService) {
        this.rentPointService = rentPointService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping("/{id}")
    public ResponseEntity<RentPointDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(rentPointService.getById(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @PostMapping
    public ResponseEntity<List<RentPointDTO>> getAll(@RequestBody(required = false) RentPointFilterDTO filter,
                                                     @RequestParam(required = false) Integer page,
                                                     @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(rentPointService.getAll(filter, page, size));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping("/{id}/scooters")
    public ResponseEntity<List<ScooterDTO>> getScooters(@PathVariable Long id,
                                                        @RequestParam(required = false) Integer page,
                                                        @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(rentPointService.scootersInRentPoint(id, page, size));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Void> add(@RequestBody @Valid RentPointDTO rentPoint, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        rentPointService.addRentPoint(rentPoint);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        rentPointService.removeRentPoint(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody @Valid RentPointDTO rentPoint, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        rentPointService.updateRentPoint(id, rentPoint);
        return ResponseEntity.noContent().build();
    }
}
