package me.smorodin.circus.controller;

import me.smorodin.circus.entity.Polzovatel;
import me.smorodin.circus.service.PolzovatelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class PolzovatelController {
    private final PolzovatelService polzovatelService;

    @Autowired
    public PolzovatelController(PolzovatelService polzovatelService) {
        this.polzovatelService = polzovatelService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Polzovatel>> getAllPolzovatels() {
        return ResponseEntity.ok(polzovatelService.getAllPolzovatels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Polzovatel> getPolzovatelById(@PathVariable Long id) {
        return ResponseEntity.ok(polzovatelService.getPolzovatelById(id));
    }

    @PostMapping
    public ResponseEntity<Polzovatel> createPolzovatel(@RequestBody Polzovatel polzovatel) {
        return ResponseEntity.ok(polzovatelService.createPolzovatel(polzovatel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Polzovatel> updatePolzovatel(@PathVariable Long id, @RequestBody Polzovatel polzovatelDetails) {
        return ResponseEntity.ok(polzovatelService.updatePolzovatel(id, polzovatelDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolzovatel(@PathVariable Long id) {
        polzovatelService.deletePolzovatel(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Polzovatel>> searchPolzovatels(@RequestParam String query) {
        return ResponseEntity.ok(polzovatelService.searchPolzovatels(query));
    }
}