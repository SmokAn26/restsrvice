package me.smorodin.circus.repository;

import me.smorodin.circus.entity.Show;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends CrudRepository<Show, Long> {
    Iterable<Show> findByNazvatieContaining(String nazvatie);
    Iterable<Show> findByVozrastOgrLessThanEqual(Integer vozrast);
    Iterable<Show> findByCenaLessThanEqual(Integer cena);
}