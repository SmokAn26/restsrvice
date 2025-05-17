package me.smorodin.circus.repository;

import me.smorodin.circus.entity.Acter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActerRepository extends CrudRepository<Acter, Long> {
    Iterable<Acter> findByFamiliaContainingOrNameContaining(String familia, String name);
}