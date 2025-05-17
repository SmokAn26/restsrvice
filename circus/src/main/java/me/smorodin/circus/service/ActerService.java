package me.smorodin.circus.service;

import me.smorodin.circus.entity.Acter;
import me.smorodin.circus.repository.ActerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActerService {
    private final ActerRepository acterRepository;

    @Autowired
    public ActerService(ActerRepository acterRepository) {
        this.acterRepository = acterRepository;
    }

    public Iterable<Acter> getAllActers() {
        return acterRepository.findAll();
    }

    public Acter getActerById(Long id) {
        return acterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Acter not found with id: " + id));
    }

    public Acter createActer(Acter acter) {
        return acterRepository.save(acter);
    }

    public Acter updateActer(Long id, Acter acterDetails) {
        Acter acter = getActerById(id);
        acter.setName(acterDetails.getName());
        acter.setFamilia(acterDetails.getFamilia());
        acter.setVozrast(acterDetails.getVozrast());

        return acterRepository.save(acter);
    }

    public void deleteActer(Long id) {
        Acter acter = getActerById(id);
        acterRepository.delete(acter);
    }

    public Iterable<Acter> searchActers(String query) {
        return acterRepository.findByFamiliaContainingOrNameContaining(query, query);
    }
}