package com.psr.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcar")
    private int idCar;

    private String registration;
    private Date regdate;
    private Double pvp;

    @ManyToOne
    @JoinColumn(name = "idowner", nullable = false)
    @JsonIgnore
    private Owner owner;

    @ManyToMany(cascade = {CascadeType.MERGE})
    // Especificamos los datos de la tabla intermedia
    @JoinTable(
            name = "modelcar", // Nombre
            joinColumns = {@JoinColumn(name = "idcar")}, // MI foreign key
            inverseJoinColumns = {@JoinColumn(name = "idmodel")} // Foreign Key de la otra entidad
    )
    Set<Model> model = new HashSet<>();

    public Car() {
    }

    public Car(String registration, Date regdate, Double pvp, Owner owner) {
        this.registration = registration;
        this.regdate = regdate;
        this.pvp = pvp;
        this.owner = owner;
    }


    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public Double getPvp() {
        return pvp;
    }

    public void setPvp(Double pvp) {
        this.pvp = pvp;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setModel(Set<Model> model) {
        this.model = model;
    }

    public Set<Model> getModel() {
        return model;
    }



    @Override
    public String toString() {
        return "Coche{" +
                "idCar=" + idCar +
                ", registration='" + registration + '\'' +
                ", regDate=" + regdate +
                ", pvp=" + pvp +
                ", owner=" + owner +
                ", model=" + model +
                '}';
    }
}
