package com.psr.models;

import jakarta.persistence.*;

import java.util.Date;

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

    @Override
    public String toString() {
        return "coche{carId=" + carId + ", registration=" + registration + ", regDate=" + regDate +
                ", pvp=" + pvp + ", owner=" + owner + '}';
    }
}
