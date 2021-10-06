package by.scooter.controller;

import by.scooter.api.sevice.ScooterService;
import by.scooter.entity.vehicle.Scooter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scooters")
@RequiredArgsConstructor
public class ScooterController {

    private final ScooterService scooterService;

    @GetMapping("/{id}")
    public ResponseEntity<Scooter> getScooterById(@PathVariable Long id) {
        return ResponseEntity.ok(scooterService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Scooter>> getScooters(@RequestParam(required = false) Integer page,
                                                     @RequestParam(required = false) Integer size) {
        if (page != null && size != null) {
            return ResponseEntity.ok(scooterService.getAll(page, size));
        }
        return ResponseEntity.ok(scooterService.getAll());
    }

    @PostMapping
    public ResponseEntity<Void> addScooter(@RequestBody Scooter scooter) {
        scooterService.addScooter(scooter);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removeScooter(@RequestParam Long id) {
        scooterService.removeScooter(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateScooter(@PathVariable Long id, @RequestBody Scooter scooter) {
        scooterService.updateScooter(id, scooter);
        return ResponseEntity.noContent().build();
    }

}
