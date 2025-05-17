package me.smorodin.circus.repository;

import me.smorodin.circus.entity.Polzovatel;
import me.smorodin.circus.entity.Status;
import me.smorodin.circus.entity.ZaprosNaLgot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZaprosNaLgotRepository extends CrudRepository<ZaprosNaLgot, Long> {
    Iterable<ZaprosNaLgot> findByPolzovatel(Polzovatel polzovatel);
    Iterable<ZaprosNaLgot> findByStatus(Status status);
}