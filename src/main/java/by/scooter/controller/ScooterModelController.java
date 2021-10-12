package by.scooter.controller;

import by.scooter.api.sevice.ScooterModelService;
import by.scooter.entity.dto.vehicle.ScooterModelDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scooters/models")
public class ScooterModelController {
    private final ScooterModelService scooterModelService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping("/{id}")
    public ResponseEntity<ScooterModelDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(scooterModelService.getById(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping
    public ResponseEntity<List<ScooterModelDTO>> getAll(@RequestParam(required = false) Integer page,
                                                     @RequestParam(required = false) Integer size) {
        if (page != null && size != null) {
            return ResponseEntity.ok(scooterModelService.getAll(page, size));
        }
        return ResponseEntity.ok(scooterModelService.getAll());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Void> add(@RequestBody ScooterModelDTO model) {
        scooterModelService.addScooterModel(model);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping
    public ResponseEntity<Void> remove(@RequestParam Long id) {
        scooterModelService.removeScooterModel(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ScooterModelDTO model) {
        scooterModelService.updateScooterModel(id, model);
        return ResponseEntity.noContent().build();
    }
}
