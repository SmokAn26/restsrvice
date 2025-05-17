package me.smorodin.circus.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ACTER")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Acter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String familia;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date vozrast;

    @ManyToMany(mappedBy = "actors")
    private List<Show> shows;


    // Конструкторы
    public Acter() {}

    public Acter(String name, String familia, Date vozrast) {
        this.name = name;
        this.familia = familia;
        this.vozrast = vozrast;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public Date getVozrast() {
        return vozrast;
    }

    public void setVozrast(Date vozrast) {
        this.vozrast = vozrast;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }
}