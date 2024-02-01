package com.psr.models;

import jakarta.persistence.*;

@Entity
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idmodel") // id de la tabla
    private int idModel;
    private String model;

    @ManyToOne
    @JoinColumn(name = "idbrand", nullable = false)
    private Brand brand;

    public Model() {
    }

    public Model(String model, Brand brand) {

        this.model = model;
        this.brand = brand;
    }

    public int getIdModel() {
        return idModel;
    }

    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Model [idModel=" + idModel +", model=" + model + "brand= " + brand + "]";
    }
}
