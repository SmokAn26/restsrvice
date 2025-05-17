package me.smorodin.circus.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "POLZOVATELI")
public class Polzovatel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String familia;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 16)
    private String telefon;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date vozrst;

    @Column(nullable = false)
    private Boolean lgota = false;

    @ManyToOne
    @JoinColumn(name = "id_role", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "polzovatel")
    @JsonManagedReference
    private List<Bilet> bilets;

    @OneToMany(mappedBy = "polzovatel")
    @JsonManagedReference
    private List<ZaprosNaLgot> zaprosNaLgots;

    // Конструкторы
    public Polzovatel() {}

    public Polzovatel(String familia, String name, String email, String password,
                      String telefon, Date vozrst, Boolean lgota, Role role) {
        this.familia = familia;
        this.name = name;
        this.email = email;
        this.password = password;
        this.telefon = telefon;
        this.vozrst = vozrst;
        this.lgota = lgota;
        this.role = role;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Date getVozrst() {
        return vozrst;
    }

    public void setVozrst(Date vozrst) {
        this.vozrst = vozrst;
    }

    public Boolean getLgota() {
        return lgota;
    }

    public void setLgota(Boolean lgota) {
        this.lgota = lgota;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}