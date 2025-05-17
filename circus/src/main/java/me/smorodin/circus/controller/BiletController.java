package me.smorodin.circus.controller;

import me.smorodin.circus.entity.Bilet;
import me.smorodin.circus.service.BiletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class BiletController {
    private final BiletService biletService;

    @Autowired
    public BiletController(BiletService biletService) {
        this.biletService = biletService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Bilet>> getAllBilets() {
        return ResponseEntity.ok(biletService.getAllBilets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bilet> getBiletById(@PathVariable Long id) {
        return ResponseEntity.ok(biletService.getBiletById(id));
    }

    @PostMapping
    public ResponseEntity<Bilet> createBilet(@RequestBody Bilet bilet) {
        return ResponseEntity.ok(biletService.createBilet(bilet));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bilet> updateBilet(@PathVariable Long id, @RequestBody Bilet biletDetails) {
        return ResponseEntity.ok(biletService.updateBilet(id, biletDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBilet(@PathVariable Long id) {
        biletService.deleteBilet(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Iterable<Bilet>> getBiletsByPolzovatel(@PathVariable Long userId) {
        return ResponseEntity.ok(biletService.getBiletsByPolzovatel(userId));
    }

    @GetMapping("/performance/{pokazId}")
    public ResponseEntity<Iterable<Bilet>> getBiletsByPokaz(@PathVariable Long pokazId) {
        return ResponseEntity.ok(biletService.getBiletsByPokaz(pokazId));
    }
}