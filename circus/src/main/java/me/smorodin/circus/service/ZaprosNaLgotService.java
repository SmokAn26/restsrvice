package me.smorodin.circus.service;

import me.smorodin.circus.entity.ZaprosNaLgot;
import me.smorodin.circus.entity.Polzovatel;
import me.smorodin.circus.entity.Status;
import me.smorodin.circus.repository.ZaprosNaLgotRepository;
import me.smorodin.circus.repository.PolzovatelRepository;
import me.smorodin.circus.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZaprosNaLgotService {
    private final ZaprosNaLgotRepository zaprosNaLgotRepository;
    private final PolzovatelRepository polzovatelRepository;
    private final StatusRepository statusRepository;

    @Autowired
    public ZaprosNaLgotService(ZaprosNaLgotRepository zaprosNaLgotRepository,
                               PolzovatelRepository polzovatelRepository,
                               StatusRepository statusRepository) {
        this.zaprosNaLgotRepository = zaprosNaLgotRepository;
        this.polzovatelRepository = polzovatelRepository;
        this.statusRepository = statusRepository;
    }

    public Iterable<ZaprosNaLgot> getAllZaprosy() {
        return zaprosNaLgotRepository.findAll();
    }

    public ZaprosNaLgot getZaprosById(Long id) {
        return zaprosNaLgotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ZaprosNaLgot not found with id: " + id));
    }

    public ZaprosNaLgot createZapros(ZaprosNaLgot zapros) {
        // По умолчанию статус "На рассмотрении"
        Status pendingStatus = statusRepository.findByName("На рассмотрении");
        if (pendingStatus == null) {
            pendingStatus = new Status("На рассмотрении");
            statusRepository.save(pendingStatus);
        }
        zapros.setStatus(pendingStatus);

        return zaprosNaLgotRepository.save(zapros);
    }

    public ZaprosNaLgot updateZapros(Long id, ZaprosNaLgot zaprosDetails) {
        ZaprosNaLgot zapros = getZaprosById(id);
        zapros.setFile(zaprosDetails.getFile());

        if (zaprosDetails.getStatus() != null) {
            Status status = statusRepository.findById(zaprosDetails.getStatus().getId())
                    .orElseThrow(() -> new RuntimeException("Status not found"));
            zapros.setStatus(status);
        }

        if (zaprosDetails.getPolzovatel() != null) {
            Polzovatel polzovatel = polzovatelRepository.findById(zaprosDetails.getPolzovatel().getId())
                    .orElseThrow(() -> new RuntimeException("Polzovatel not found"));
            zapros.setPolzovatel(polzovatel);
        }

        return zaprosNaLgotRepository.save(zapros);
    }

    public void deleteZapros(Long id) {
        ZaprosNaLgot zapros = getZaprosById(id);
        zaprosNaLgotRepository.delete(zapros);
    }

    public Iterable<ZaprosNaLgot> getZaprosyByPolzovatel(Long polzovatelId) {
        Polzovatel polzovatel = polzovatelRepository.findById(polzovatelId)
                .orElseThrow(() -> new RuntimeException("Polzovatel not found with id: " + polzovatelId));
        return zaprosNaLgotRepository.findByPolzovatel(polzovatel);
    }

    public Iterable<ZaprosNaLgot> getZaprosyByStatus(Long statusId) {
        Status status = statusRepository.findById(statusId)
                .orElseThrow(() -> new RuntimeException("Status not found with id: " + statusId));
        return zaprosNaLgotRepository.findByStatus(status);
    }

    public ZaprosNaLgot processZapros(Long id, Long statusId) {
        ZaprosNaLgot zapros = getZaprosById(id);
        Status status = statusRepository.findById(statusId)
                .orElseThrow(() -> new RuntimeException("Status not found with id: " + statusId));

        zapros.setStatus(status);

        // Если статус "Одобрено", обновляем флаг льготы у пользователя
        if (status.getName().equals("Одобрено") || status.getName().equals("Активен")) {
            Polzovatel polzovatel = zapros.getPolzovatel();
            polzovatel.setLgota(true);
            polzovatelRepository.save(polzovatel);
        }

        return zaprosNaLgotRepository.save(zapros);
    }
}