package by.scooter.controller;

import by.scooter.api.sevice.ScooterModelService;
import by.scooter.dto.vehicle.ScooterModelDTO;
import by.scooter.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/scooters/models")
public class ScooterModelController {

    private final ScooterModelService scooterModelService;

    @Autowired
    public ScooterModelController(ScooterModelService scooterModelService) {
        this.scooterModelService = scooterModelService;
    }

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
    public ResponseEntity<Void> add(@RequestBody @Valid ScooterModelDTO model, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        scooterModelService.addScooterModel(model);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        scooterModelService.removeScooterModel(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody @Valid ScooterModelDTO model, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        scooterModelService.updateScooterModel(id, model);
        return ResponseEntity.noContent().build();
    }
}
