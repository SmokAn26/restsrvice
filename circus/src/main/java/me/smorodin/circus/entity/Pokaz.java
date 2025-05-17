package me.smorodin.circus.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "POKAZ")
public class Pokaz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_show", nullable = false)
    @JsonBackReference
    private Show show;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(nullable = false)
    private Time time;

    @OneToMany(mappedBy = "pokaz")
    @JsonManagedReference
    private List<Bilet> bilets;

    // Конструкторы
    public Pokaz() {}

    public Pokaz(Show show, Date date, Time time) {
        this.show = show;
        this.date = date;
        this.time = time;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public List<Bilet> getBilets() {
        return bilets;
    }

    public void setBilets(List<Bilet> bilets) {
        this.bilets = bilets;
    }
}