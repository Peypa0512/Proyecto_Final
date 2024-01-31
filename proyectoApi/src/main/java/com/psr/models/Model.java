package com.psr.models;

import jakarta.persistence.*;

@Entity
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int modelId;
    private String model;

    @ManyToOne
    @JoinColumn(name = "brandId", nullable = false)
    private Brand brand;

    public Model() {
    }

    public Model(String model, Brand brand) {

        this.model = model;
        this.brand = brand;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
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
        return "Model [modelId=" + modelId +", model=" + model + "brand= " + brand + "]";
    }
}
