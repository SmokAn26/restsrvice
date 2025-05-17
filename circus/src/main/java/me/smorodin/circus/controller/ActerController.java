package me.smorodin.circus.controller;

import me.smorodin.circus.entity.Acter;
import me.smorodin.circus.service.ActerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/actors")
public class ActerController {
    private final ActerService acterService;

    @Autowired
    public ActerController(ActerService acterService) {
        this.acterService = acterService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Acter>> getAllActers() {
        return ResponseEntity.ok(acterService.getAllActers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Acter> getActerById(@PathVariable Long id) {
        return ResponseEntity.ok(acterService.getActerById(id));
    }

    @PostMapping
    public ResponseEntity<Acter> createActer(@RequestBody Acter acter) {
        return ResponseEntity.ok(acterService.createActer(acter));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Acter> updateActer(@PathVariable Long id, @RequestBody Acter acterDetails) {
        return ResponseEntity.ok(acterService.updateActer(id, acterDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActer(@PathVariable Long id) {
        acterService.deleteActer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Acter>> searchActers(@RequestParam String query) {
        return ResponseEntity.ok(acterService.searchActers(query));
    }
}