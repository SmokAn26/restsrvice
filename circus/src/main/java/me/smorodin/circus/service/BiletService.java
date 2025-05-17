package me.smorodin.circus.service;

import me.smorodin.circus.entity.Bilet;
import me.smorodin.circus.entity.Pokaz;
import me.smorodin.circus.entity.Polzovatel;
import me.smorodin.circus.repository.BiletRepository;
import me.smorodin.circus.repository.PokazRepository;
import me.smorodin.circus.repository.PolzovatelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiletService {
    private final BiletRepository biletRepository;
    private final PokazRepository pokazRepository;
    private final PolzovatelRepository polzovatelRepository;

    @Autowired
    public BiletService(BiletRepository biletRepository, PokazRepository pokazRepository, PolzovatelRepository polzovatelRepository) {
        this.biletRepository = biletRepository;
        this.pokazRepository = pokazRepository;
        this.polzovatelRepository = polzovatelRepository;
    }

    public Iterable<Bilet> getAllBilets() {
        return biletRepository.findAll();
    }

    public Bilet getBiletById(Long id) {
        return biletRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bilet not found with id: " + id));
    }

    public Bilet createBilet(Bilet bilet) {
        // Проверка на доступность места
        Iterable<Bilet> existingBilets = biletRepository.findByRiadAndMestoAndPokaz(
                bilet.getRiad(), bilet.getMesto(), bilet.getPokaz());

        if (existingBilets.iterator().hasNext()) {
            throw new RuntimeException("This seat is already taken");
        }

        // Расчет цены с учетом скидки
        if (bilet.getPolzovatel().getLgota()) {
            int skidka = bilet.getSkidka() > 0 ? bilet.getSkidka() : 20; // 20% скидка по умолчанию для льготников
            bilet.setSkidka(skidka);
        }

        int baseCena = bilet.getPokaz().getShow().getCena();
        int finalCena = baseCena - (baseCena * bilet.getSkidka() / 100);
        bilet.setCena(finalCena);

        return biletRepository.save(bilet);
    }

    public Bilet updateBilet(Long id, Bilet biletDetails) {
        Bilet bilet = getBiletById(id);
        bilet.setRiad(biletDetails.getRiad());
        bilet.setMesto(biletDetails.getMesto());
        bilet.setSkidka(biletDetails.getSkidka());
        bilet.setCena(biletDetails.getCena());

        if (biletDetails.getPokaz() != null) {
            Pokaz pokaz = pokazRepository.findById(biletDetails.getPokaz().getId())
                    .orElseThrow(() -> new RuntimeException("Pokaz not found"));
            bilet.setPokaz(pokaz);
        }

        if (biletDetails.getPolzovatel() != null) {
            Polzovatel polzovatel = polzovatelRepository.findById(biletDetails.getPolzovatel().getId())
                    .orElseThrow(() -> new RuntimeException("Polzovatel not found"));
            bilet.setPolzovatel(polzovatel);
        }

        return biletRepository.save(bilet);
    }

    public void deleteBilet(Long id) {
        Bilet bilet = getBiletById(id);
        biletRepository.delete(bilet);
    }

    public Iterable<Bilet> getBiletsByPolzovatel(Long polzovatelId) {
        Polzovatel polzovatel = polzovatelRepository.findById(polzovatelId)
                .orElseThrow(() -> new RuntimeException("Polzovatel not found with id: " + polzovatelId));
        return biletRepository.findByPolzovatel(polzovatel);
    }

    public Iterable<Bilet> getBiletsByPokaz(Long pokazId) {
        Pokaz pokaz = pokazRepository.findById(pokazId)
                .orElseThrow(() -> new RuntimeException("Pokaz not found with id: " + pokazId));
        return biletRepository.findByPokaz(pokaz);
    }
}