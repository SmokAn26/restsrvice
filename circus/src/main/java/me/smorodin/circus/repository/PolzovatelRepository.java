package me.smorodin.circus.repository;

import me.smorodin.circus.entity.Polzovatel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PolzovatelRepository extends CrudRepository<Polzovatel, Long> {
    Optional<Polzovatel> findByEmail(String email);
    Iterable<Polzovatel> findByFamiliaContainingOrNameContaining(String familia, String name);
}