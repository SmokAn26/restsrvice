package me.smorodin.circus.controller;

import me.smorodin.circus.entity.Pokaz;
import me.smorodin.circus.service.PokazService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/performances")
public class PokazController {
    private final PokazService pokazService;

    @Autowired
    public PokazController(PokazService pokazService) {
        this.pokazService = pokazService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Pokaz>> getAllPokazs() {
        return ResponseEntity.ok(pokazService.getAllPokazs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokaz> getPokazById(@PathVariable Long id) {
        return ResponseEntity.ok(pokazService.getPokazById(id));
    }

    @PostMapping
    public ResponseEntity<Pokaz> createPokaz(@RequestBody Pokaz pokaz) {
        return ResponseEntity.ok(pokazService.createPokaz(pokaz));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pokaz> updatePokaz(@PathVariable Long id, @RequestBody Pokaz pokazDetails) {
        return ResponseEntity.ok(pokazService.updatePokaz(id, pokazDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokaz(@PathVariable Long id) {
        pokazService.deletePokaz(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/show/{showId}")
    public ResponseEntity<Iterable<Pokaz>> getPokazsByShow(@PathVariable Long showId) {
        return ResponseEntity.ok(pokazService.getPokazsByShow(showId));
    }

    @GetMapping("/future")
    public ResponseEntity<Iterable<Pokaz>> getFuturePokazs() {
        return ResponseEntity.ok(pokazService.getFuturePokazs());
    }

    @GetMapping("/future/show/{showId}")
    public ResponseEntity<Iterable<Pokaz>> getFuturePokazsByShow(@PathVariable Long showId) {
        return ResponseEntity.ok(pokazService.getFuturePokazsByShow(showId));
    }
}