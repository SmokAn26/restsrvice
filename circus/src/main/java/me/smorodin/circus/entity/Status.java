package me.smorodin.circus.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "STATUS")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "status")
    private List<ZaprosNaLgot> zaprosNaLgots;

    // Конструкторы
    public Status() {}

    public Status(String name) {
        this.name = name;
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

    public List<ZaprosNaLgot> getZaprosNaLgots() {
        return zaprosNaLgots;
    }

    public void setZaprosNaLgots(List<ZaprosNaLgot> zaprosNaLgots) {
        this.zaprosNaLgots = zaprosNaLgots;
    }
}