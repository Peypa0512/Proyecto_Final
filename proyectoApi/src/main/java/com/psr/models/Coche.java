package com.psr.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Coche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coche_id", nullable = true)
    private int carId;

    @Column(name="matricula",nullable = true, unique=true)
    private String registration;
    @Column(name="fecha_matriculacion", nullable = true)
    private Date regDate;

    @Column(name="precio", nullable = true)
    private Double pvp;

    public Coche() {
    }

    public Coche(String registration, Date regDate, Double pvp) {
        this.registration = registration;
        this.regDate = regDate;
        this.pvp = pvp;
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

    @Override
    public String toString() {
        return "coche{" +
                "carId=" + carId +
                ", registration='" + registration + '\'' +
                ", regDate=" + regDate +
                ", pvp=" + pvp +
                '}';
    }
}
