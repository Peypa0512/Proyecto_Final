package com.psr.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Coche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coche_id", nullable = true)
    private int carId;

    @Column(name="matricula",nullable = false, unique=true)
    private String registration;
    @Column(name="fecha_matriculacion", nullable = false)
    private Date regDate;

    @Column(name="precio", nullable = false)
    private Double pvp;

    @ManyToOne
    @JoinColumn(name = "propietario_id", nullable = false)
    private Propietario owner;

    @ManyToMany(cascade = {CascadeType.MERGE})
    // Especificamos los datos de la tabla intermedia
    @JoinTable(
            name = "modelo_coche", // NOmbre
            joinColumns = {@JoinColumn(name = "carid")}, // MI foreign key
            inverseJoinColumns = {@JoinColumn(name = "modelid")} // Foreign Key de la otra entidad
    )
    Set<Modelo> model = new HashSet<>();

    public Coche() {
    }

    public Coche(String registration, Date regDate, Double pvp, Propietario owner) {
        this.registration = registration;
        this.regDate = regDate;
        this.pvp = pvp;
        this.owner = owner;
    }


    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Double getPvp() {
        return pvp;
    }

    public void setPvp(Double pvp) {
        this.pvp = pvp;
    }

    public Propietario getOwner() {
        return owner;
    }

    public void setOwner(Propietario owner) {
        this.owner = owner;
    }

    public Set<Modelo> getModel() {
        return model;
    }

    public void setModel(Set<Modelo> model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "carId=" + carId +
                ", registration='" + registration + '\'' +
                ", regDate=" + regDate +
                ", pvp=" + pvp +
                ", owner=" + owner +
                ", model=" + model +
                '}';
    }
}
