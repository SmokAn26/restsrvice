package me.smorodin.circus.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "ZAPROS_NA_LGOT")
public class ZaprosNaLgot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String file = "0";

    @ManyToOne
    @JoinColumn(name = "id_status", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_polzovatel", nullable = false)
    @JsonBackReference
    private Polzovatel polzovatel;

    // Конструкторы
    public ZaprosNaLgot() {}

    public ZaprosNaLgot(String file, Status status, Polzovatel polzovatel) {
        this.file = file;
        this.status = status;
        this.polzovatel = polzovatel;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Polzovatel getPolzovatel() {
        return polzovatel;
    }

    public void setPolzovatel(Polzovatel polzovatel) {
        this.polzovatel = polzovatel;
    }
}