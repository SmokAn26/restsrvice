package me.smorodin.circus.service;

import me.smorodin.circus.entity.Pokaz;
import me.smorodin.circus.entity.Show;
import me.smorodin.circus.repository.PokazRepository;
import me.smorodin.circus.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PokazService {
    private final PokazRepository pokazRepository;
    private final ShowRepository showRepository;

    @Autowired
    public PokazService(PokazRepository pokazRepository, ShowRepository showRepository) {
        this.pokazRepository = pokazRepository;
        this.showRepository = showRepository;
    }

    public Iterable<Pokaz> getAllPokazs() {
        return pokazRepository.findAll();
    }

    public Pokaz getPokazById(Long id) {
        return pokazRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pokaz not found with id: " + id));
    }

    public Pokaz createPokaz(Pokaz pokaz) {
        return pokazRepository.save(pokaz);
    }

    public Pokaz updatePokaz(Long id, Pokaz pokazDetails) {
        Pokaz pokaz = getPokazById(id);
        pokaz.setDate(pokazDetails.getDate());
        pokaz.setTime(pokazDetails.getTime());

        if (pokazDetails.getShow() != null) {
            Show show = showRepository.findById(pokazDetails.getShow().getId())
                    .orElseThrow(() -> new RuntimeException("Show not found"));
            pokaz.setShow(show);
        }

        return pokazRepository.save(pokaz);
    }

    public void deletePokaz(Long id) {
        Pokaz pokaz = getPokazById(id);
        pokazRepository.delete(pokaz);
    }

    public Iterable<Pokaz> getPokazsByShow(Long showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found with id: " + showId));
        return pokazRepository.findByShow(show);
    }

    public Iterable<Pokaz> getFuturePokazs() {
        Date today = new Date();
        return pokazRepository.findByDateGreaterThanEqual(today);
    }

    public Iterable<Pokaz> getFuturePokazsByShow(Long showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found with id: " + showId));
        Date today = new Date();
        return pokazRepository.findByShowAndDateGreaterThanEqual(show, today);
    }
}