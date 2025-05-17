package me.smorodin.circus.controller;

import me.smorodin.circus.entity.ZaprosNaLgot;
import me.smorodin.circus.service.ZaprosNaLgotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discount-requests")
public class ZaprosNaLgotController {
    private final ZaprosNaLgotService zaprosNaLgotService;

    @Autowired
    public ZaprosNaLgotController(ZaprosNaLgotService zaprosNaLgotService) {
        this.zaprosNaLgotService = zaprosNaLgotService;
    }

    @GetMapping
    public ResponseEntity<Iterable<ZaprosNaLgot>> getAllZaprosy() {
        return ResponseEntity.ok(zaprosNaLgotService.getAllZaprosy());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZaprosNaLgot> getZaprosById(@PathVariable Long id) {
        return ResponseEntity.ok(zaprosNaLgotService.getZaprosById(id));
    }

    @PostMapping
    public ResponseEntity<ZaprosNaLgot> createZapros(@RequestBody ZaprosNaLgot zapros) {
        return ResponseEntity.ok(zaprosNaLgotService.createZapros(zapros));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZaprosNaLgot> updateZapros(@PathVariable Long id, @RequestBody ZaprosNaLgot zaprosDetails) {
        return ResponseEntity.ok(zaprosNaLgotService.updateZapros(id, zaprosDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZapros(@PathVariable Long id) {
        zaprosNaLgotService.deleteZapros(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Iterable<ZaprosNaLgot>> getZaprosyByPolzovatel(@PathVariable Long userId) {
        return ResponseEntity.ok(zaprosNaLgotService.getZaprosyByPolzovatel(userId));
    }

    @GetMapping("/status/{statusId}")
    public ResponseEntity<Iterable<ZaprosNaLgot>> getZaprosyByStatus(@PathVariable Long statusId) {
        return ResponseEntity.ok(zaprosNaLgotService.getZaprosyByStatus(statusId));
    }

    @PutMapping("/{id}/process/{statusId}")
    public ResponseEntity<ZaprosNaLgot> processZapros(@PathVariable Long id, @PathVariable Long statusId) {
        return ResponseEntity.ok(zaprosNaLgotService.processZapros(id, statusId));
    }
}