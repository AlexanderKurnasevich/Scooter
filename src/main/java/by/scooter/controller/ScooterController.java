package by.scooter.controller;

import by.scooter.api.sevice.ScooterService;
import by.scooter.entity.dto.vehicle.ScooterDTO;
import by.scooter.exception.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/scooters")
@RequiredArgsConstructor
public class ScooterController {

    private final ScooterService scooterService;

    @GetMapping("/{id}")
    public ResponseEntity<ScooterDTO> getScooterById(@PathVariable Long id) {
        return ResponseEntity.ok(scooterService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ScooterDTO>> getScooters(@RequestParam(required = false) Integer page,
                                                        @RequestParam(required = false) Integer size) {
        if (page != null && size != null) {
            return ResponseEntity.ok(scooterService.getAll(page, size));
        }
        return ResponseEntity.ok(scooterService.getAll());
    }

    @PostMapping
    public ResponseEntity<Void> addScooter(@RequestBody @Valid ScooterDTO scooter, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationError(result, scooter);
        }

        scooterService.addScooter(scooter);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removeScooter(@RequestParam Long id) {
        scooterService.removeScooter(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateScooter(@PathVariable Long id,
                                              @RequestBody @Valid ScooterDTO scooter, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationError(result, scooter);
        }

        scooterService.updateScooter(id, scooter);
        return ResponseEntity.noContent().build();
    }

}
