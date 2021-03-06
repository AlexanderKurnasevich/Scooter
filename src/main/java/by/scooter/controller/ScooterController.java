package by.scooter.controller;

import by.scooter.api.sevice.ScooterService;
import by.scooter.dto.vehicle.ScooterDTO;
import by.scooter.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/scooters")
public class ScooterController {

    private final ScooterService scooterService;

    @Autowired
    public ScooterController(ScooterService scooterService) {
        this.scooterService = scooterService;
    }

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
            throw new ValidationException(result);
        }

        scooterService.addScooter(scooter);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeScooter(@PathVariable Long id) {
        scooterService.removeScooter(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateScooter(@PathVariable Long id,
                                              @RequestBody @Valid ScooterDTO scooter, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        scooterService.updateScooter(id, scooter);
        return ResponseEntity.noContent().build();
    }

}
