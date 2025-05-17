package me.smorodin.circus.service;

import me.smorodin.circus.entity.Show;
import me.smorodin.circus.entity.Acter;
import me.smorodin.circus.repository.ShowRepository;
import me.smorodin.circus.repository.ActerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {
    private final ShowRepository showRepository;
    private final ActerRepository acterRepository;

    @Autowired
    public ShowService(ShowRepository showRepository, ActerRepository acterRepository) {
        this.showRepository = showRepository;
        this.acterRepository = acterRepository;
    }

    public Iterable<Show> getAllShows() {
        return showRepository.findAll();
    }

    public Show getShowById(Long id) {
        return showRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Show not found with id: " + id));
    }

    public Show createShow(Show show) {
        return showRepository.save(show);
    }

    public Show updateShow(Long id, Show showDetails) {
        Show show = getShowById(id);
        show.setNazvatie(showDetails.getNazvatie());
        show.setCena(showDetails.getCena());
        show.setOpisanie(showDetails.getOpisanie());
        show.setVozrastOgr();
        show.setPhoto(showDetails.getPhoto());

        return showRepository.save(show);
    }

    public void deleteShow(Long id) {
        Show show = getShowById(id);
        showRepository.delete(show);
    }

    public Iterable<Show> searchShows(String query) {
        return showRepository.findByNazvatieContaining(query);
    }

    public Iterable<Show> getShowsByAgeLimit(Integer age) {
        return showRepository.findByVozrastOgrLessThanEqual(age);
    }

    public Iterable<Show> getShowsByMaxPrice(Integer price) {
        return showRepository.findByCenaLessThanEqual(price);
    }

    public Show addActerToShow(Long showId, Long acterId) {
        Show show = getShowById(showId);
        Acter acter = acterRepository.findById(acterId)
                .orElseThrow(() -> new RuntimeException("Acter not found with id: " + acterId));

        List<Acter> actors = show.getActors();
        if (!actors.contains(acter)) {
            actors.add(acter);
            show.setActors(actors);
            return showRepository.save(show);
        }

        return show;
    }

    public Show removeActerFromShow(Long showId, Long acterId) {
        Show show = getShowById(showId);
        Acter acter = acterRepository.findById(acterId)
                .orElseThrow(() -> new RuntimeException("Acter not found with id: " + acterId));

        List<Acter> actors = show.getActors();
        if (actors.contains(acter)) {
            actors.remove(acter);
            show.setActors(actors);
            return showRepository.save(show);
        }

        return show;
    }
}