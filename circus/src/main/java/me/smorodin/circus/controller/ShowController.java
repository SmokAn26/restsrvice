package me.smorodin.circus.controller;

import me.smorodin.circus.entity.Show;
import me.smorodin.circus.service.ShowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shows")
public class ShowController {
    private static final Logger logger = LoggerFactory.getLogger(ShowController.class);

    private final ShowService showService;

    @Autowired
    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Show>> getAllShows() {
        logger.info("GET request to fetch all shows");
        try {
            Iterable<Show> shows = showService.getAllShows();
            return ResponseEntity.ok(shows);
        } catch (Exception e) {
            logger.error("Error fetching all shows", e);
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Show> getShowById(@PathVariable Long id) {
        logger.info("GET request to fetch show with id: {}", id);
        return ResponseEntity.ok(showService.getShowById(id));
    }

    @PostMapping
    public ResponseEntity<Show> createShow(@RequestBody Show show) {
        logger.info("POST request to create a new show");
        return ResponseEntity.ok(showService.createShow(show));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Show> updateShow(@PathVariable Long id, @RequestBody Show showDetails) {
        logger.info("PUT request to update show with id: {}", id);
        return ResponseEntity.ok(showService.updateShow(id, showDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id) {
        logger.info("DELETE request to delete show with id: {}", id);
        showService.deleteShow(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Show>> searchShows(@RequestParam String query) {
        logger.info("GET request to search shows with query: {}", query);
        return ResponseEntity.ok(showService.searchShows(query));
    }

    @GetMapping("/byAge")
    public ResponseEntity<Iterable<Show>> getShowsByAgeLimit(@RequestParam Integer age) {
        logger.info("GET request to fetch shows by age limit: {}", age);
        return ResponseEntity.ok(showService.getShowsByAgeLimit(age));
    }

    @GetMapping("/byPrice")
    public ResponseEntity<Iterable<Show>> getShowsByMaxPrice(@RequestParam Integer price) {
        logger.info("GET request to fetch shows by max price: {}", price);
        return ResponseEntity.ok(showService.getShowsByMaxPrice(price));
    }

    @PostMapping("/{showId}/actors/{acterId}")
    public ResponseEntity<Show> addActerToShow(@PathVariable Long showId, @PathVariable Long acterId) {
        logger.info("POST request to add actor {} to show {}", acterId, showId);
        return ResponseEntity.ok(showService.addActerToShow(showId, acterId));
    }

    @DeleteMapping("/{showId}/actors/{acterId}")
    public ResponseEntity<Show> removeActerFromShow(@PathVariable Long showId, @PathVariable Long acterId) {
        logger.info("DELETE request to remove actor {} from show {}", acterId, showId);
        return ResponseEntity.ok(showService.removeActerFromShow(showId, acterId));
    }
}