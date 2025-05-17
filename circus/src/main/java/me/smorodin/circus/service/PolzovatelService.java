package me.smorodin.circus.service;

import me.smorodin.circus.entity.Polzovatel;
import me.smorodin.circus.entity.Role;
import me.smorodin.circus.repository.PolzovatelRepository;
import me.smorodin.circus.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolzovatelService {
    private final PolzovatelRepository polzovatelRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public PolzovatelService(PolzovatelRepository polzovatelRepository, RoleRepository roleRepository) {
        this.polzovatelRepository = polzovatelRepository;
        this.roleRepository = roleRepository;
    }

    public Iterable<Polzovatel> getAllPolzovatels() {
        return polzovatelRepository.findAll();
    }

    public Polzovatel getPolzovatelById(Long id) {
        return polzovatelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Polzovatel not found with id: " + id));
    }

    public Polzovatel createPolzovatel(Polzovatel polzovatel) {
        return polzovatelRepository.save(polzovatel);
    }

    public Polzovatel updatePolzovatel(Long id, Polzovatel polzovatelDetails) {
        Polzovatel polzovatel = getPolzovatelById(id);
        polzovatel.setFamilia(polzovatelDetails.getFamilia());
        polzovatel.setName(polzovatelDetails.getName());
        polzovatel.setEmail(polzovatelDetails.getEmail());
        polzovatel.setPassword(polzovatelDetails.getPassword());
        polzovatel.setTelefon(polzovatelDetails.getTelefon());
        polzovatel.setVozrst(polzovatelDetails.getVozrst());
        polzovatel.setLgota(polzovatelDetails.getLgota());

        if (polzovatelDetails.getRole() != null) {
            Role role = roleRepository.findById(polzovatelDetails.getRole().getId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            polzovatel.setRole(role);
        }

        return polzovatelRepository.save(polzovatel);
    }

    public void deletePolzovatel(Long id) {
        Polzovatel polzovatel = getPolzovatelById(id);
        polzovatelRepository.delete(polzovatel);
    }

    public Iterable<Polzovatel> searchPolzovatels(String query) {
        return polzovatelRepository.findByFamiliaContainingOrNameContaining(query, query);
    }
}