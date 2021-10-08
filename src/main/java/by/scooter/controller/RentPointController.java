package by.scooter.controller;

import by.scooter.api.sevice.RentPointService;
import by.scooter.entity.dto.RentPointFilterDTO;
import by.scooter.entity.location.RentPoint;
import by.scooter.entity.vehicle.Scooter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/points")
public class RentPointController {
    private final RentPointService rentPointService;

    @GetMapping("/{id}")
    public ResponseEntity<RentPoint> getById(@PathVariable Long id) {
        return ResponseEntity.ok(rentPointService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<RentPoint>> getAll(@RequestBody RentPointFilterDTO filter,
                                                  @RequestParam(required = false) Integer page,
                                                  @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(rentPointService.getAll(filter, page, size));
    }

    @GetMapping("/{id}/scooters")
    public ResponseEntity<List<Scooter>> getScooters(@PathVariable Long id,
                                                     @RequestParam(required = false) Integer page,
                                                     @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(rentPointService.scootersInRentPoint(id, page, size));
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody RentPoint rentPoint) {
        rentPointService.addRentPoint(rentPoint);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> remove(@RequestParam Long id) {
        rentPointService.removeRentPoint(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody RentPoint rentPoint) {
        rentPointService.updateRentPoint(id, rentPoint);
        return ResponseEntity.noContent().build();
    }
}
