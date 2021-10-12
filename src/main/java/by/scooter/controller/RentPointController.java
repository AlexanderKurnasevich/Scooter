package by.scooter.controller;

import by.scooter.api.sevice.RentPointService;
import by.scooter.entity.dto.location.RentPointDTO;
import by.scooter.entity.dto.location.RentPointFilterDTO;
import by.scooter.entity.dto.vehicle.ScooterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/points")
public class RentPointController {
    private final RentPointService rentPointService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping("/{id}")
    public ResponseEntity<RentPointDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(rentPointService.getById(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping
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
    @PostMapping
    public ResponseEntity<Void> add(@RequestBody RentPointDTO rentPoint) {
        rentPointService.addRentPoint(rentPoint);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping
    public ResponseEntity<Void> remove(@RequestParam Long id) {
        rentPointService.removeRentPoint(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody RentPointDTO rentPoint) {
        rentPointService.updateRentPoint(id, rentPoint);
        return ResponseEntity.noContent().build();
    }
}
