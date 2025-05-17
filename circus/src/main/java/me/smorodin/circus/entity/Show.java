package me.smorodin.circus.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "SHOW")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nazvatie;

    @Column(nullable = false)
    private Integer cena;

    @Column(nullable = false, length = 200)
    private String opisanie;

    @Column(name = "vozrast_ogr", nullable = false)
    private Integer vozrastOgr;

    @Column(nullable = false, length = 150)
    private String photo;

    @OneToMany(mappedBy = "show")
    @JsonManagedReference
    private List<Pokaz> pokazy;

    @ManyToMany
    @JoinTable(
            name = "ACTER_PREDSTAVLENIE",
            joinColumns = @JoinColumn(name = "id_show"),
            inverseJoinColumns = @JoinColumn(name = "id_acter")
    )
    private List<Acter> actors;

    // Конструкторы
    public Show() {}

    public Show(String nazvatie, Integer cena, String opisanie, Integer vozrastOgr, String photo) {
        this.nazvatie = nazvatie;
        this.cena = cena;
        this.opisanie = opisanie;
        this.vozrastOgr = vozrastOgr;
        this.photo = photo;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazvatie() {
        return nazvatie;
    }

    public void setNazvatie(String nazvatie) {
        this.nazvatie = nazvatie;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public String getOpisanie() {
        return opisanie;
    }

    public void setOpisanie(String opisanie) {
        this.opisanie = opisanie;
    }

    public Integer getVozrastOgr() {
        return vozrastOgr;
    }

    public void setVozrastOgr() {
        this.vozrastOgr = vozrastOgr;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Pokaz> getPokazy() {
        return pokazy;
    }

    public void setPokazy(List<Pokaz> pokazy) {
        this.pokazy = pokazy;
    }

    public List<Acter> getActors() {
        return actors;
    }

    public void setActors(List<Acter> actors) {
        this.actors = actors;
    }
}