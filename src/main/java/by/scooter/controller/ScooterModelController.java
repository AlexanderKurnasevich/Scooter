package by.scooter.controller;

import by.scooter.api.sevice.ScooterModelService;
import by.scooter.entity.vehicle.ScooterModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scooters/models")
public class ScooterModelController {
    private final ScooterModelService scooterModelService;

    @GetMapping("/{id}")
    public ResponseEntity<ScooterModel> getById(@PathVariable Long id) {
        return ResponseEntity.ok(scooterModelService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ScooterModel>> getAll(@RequestParam(required = false) Integer page,
                                                     @RequestParam(required = false) Integer size) {
        if (page != null && size != null) {
            return ResponseEntity.ok(scooterModelService.getAll(page, size));
        }
        return ResponseEntity.ok(scooterModelService.getAll());
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody ScooterModel model) {
        scooterModelService.addScooterModel(model);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> remove(@RequestParam Long id) {
        scooterModelService.removeScooterModel(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ScooterModel model) {
        scooterModelService.updateScooterModel(id, model);
        return ResponseEntity.noContent().build();
    }
}
