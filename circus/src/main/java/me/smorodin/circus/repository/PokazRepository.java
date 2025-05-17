package me.smorodin.circus.repository;

import me.smorodin.circus.entity.Pokaz;
import me.smorodin.circus.entity.Show;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;

@Repository
public interface PokazRepository extends CrudRepository<Pokaz, Long> {
    Iterable<Pokaz> findByShow(Show show);
    Iterable<Pokaz> findByDateGreaterThanEqual(Date date);
    Iterable<Pokaz> findByShowAndDateGreaterThanEqual(Show show, Date date);
}