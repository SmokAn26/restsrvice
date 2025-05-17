package me.smorodin.circus.repository;

import me.smorodin.circus.entity.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {
    Status findByName(String name);
}