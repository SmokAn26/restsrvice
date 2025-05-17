package me.smorodin.circus.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "BILET")
public class Bilet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer riad;

    @Column(nullable = false)
    private Integer mesto;

    @ManyToOne
    @JoinColumn(name = "id_pokaz", nullable = false)
    @JsonBackReference
    private Pokaz pokaz;

    @ManyToOne
    @JoinColumn(name = "id_polzovatel", nullable = false)
    private Polzovatel polzovatel;

    @Column(nullable = false)
    private Integer skidka = 0;

    @Column(nullable = false)
    private Integer cena;

    // Конструкторы
    public Bilet() {}

    public Bilet(Integer riad, Integer mesto, Pokaz pokaz, Polzovatel polzovatel, Integer skidka, Integer cena) {
        this.riad = riad;
        this.mesto = mesto;
        this.pokaz = pokaz;
        this.polzovatel = polzovatel;
        this.skidka = skidka;
        this.cena = cena;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRiad() {
        return riad;
    }

    public void setRiad(Integer riad) {
        this.riad = riad;
    }

    public Integer getMesto() {
        return mesto;
    }

    public void setMesto(Integer mesto) {
        this.mesto = mesto;
    }

    public Pokaz getPokaz() {
        return pokaz;
    }

    public void setPokaz(Pokaz pokaz) {
        this.pokaz = pokaz;
    }

    public Polzovatel getPolzovatel() {
        return polzovatel;
    }

    public void setPolzovatel(Polzovatel polzovatel) {
        this.polzovatel = polzovatel;
    }

    public Integer getSkidka() {
        return skidka;
    }

    public void setSkidka(Integer skidka) {
        this.skidka = skidka;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }
}