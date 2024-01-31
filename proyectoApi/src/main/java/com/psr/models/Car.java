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
    private int carId;

    private String registration;
    private Date regDate;
    private Double pvp;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonIgnore
    private Owner owner;

    @ManyToMany(cascade = {CascadeType.MERGE})
    // Especificamos los datos de la tabla intermedia
    @JoinTable(
            name = "model_car", // NOmbre
            joinColumns = {@JoinColumn(name = "carid")}, // MI foreign key
            inverseJoinColumns = {@JoinColumn(name = "modelid")} // Foreign Key de la otra entidad
    )
    Set<Model> model = new HashSet<>();

    public Car() {
    }

    public Car(String registration, Date regDate, Double pvp, Owner owner) {
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
                "carId=" + carId +
                ", registration='" + registration + '\'' +
                ", regDate=" + regDate +
                ", pvp=" + pvp +
                ", owner=" + owner +
                ", model=" + model +
                '}';
    }
}
