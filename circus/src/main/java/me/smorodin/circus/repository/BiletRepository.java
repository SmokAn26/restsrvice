package me.smorodin.circus.repository;

import me.smorodin.circus.entity.Bilet;
import me.smorodin.circus.entity.Pokaz;
import me.smorodin.circus.entity.Polzovatel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiletRepository extends CrudRepository<Bilet, Long> {
    Iterable<Bilet> findByPolzovatel(Polzovatel polzovatel);
    Iterable<Bilet> findByPokaz(Pokaz pokaz);
    Iterable<Bilet> findByRiadAndMestoAndPokaz(Integer riad, Integer mesto, Pokaz pokaz);
}