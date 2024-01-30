package com.psr.models;

import jakarta.persistence.*;

@Entity
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpack;
    private String color;
    private String engine;
    private int cv;

    @Column(name = "car_finish")
    private String carFinish;

    @ManyToOne
    @JoinColumn(name = "modelo_id", nullable = false)
    private Modelo model;

    public Pack() {
    }

    public Pack(int idpack, String color, String engine, int cv, String carFinish, Modelo model) {
        this.idpack = idpack;
        this.color = color;
        this.engine = engine;
        this.cv = cv;
        this.carFinish = carFinish;
        this.model = model;
    }

    public int getIdpack() {
        return idpack;
    }

    public void setIdpack(int idpack) {
        this.idpack = idpack;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public String getCarFinish() {
        return carFinish;
    }

    public void setCarFinish(String carFinish) {
        this.carFinish = carFinish;
    }

    public Modelo getModel() {
        return model;
    }

    public void setModel(Modelo model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Pack{" +
                "idpack=" + idpack +
                ", color='" + color + '\'' +
                ", engine='" + engine + '\'' +
                ", cv=" + cv +
                ", carFinish='" + carFinish + '\'' +
                ", model=" + model.getModel() +
                '}';
    }
}
