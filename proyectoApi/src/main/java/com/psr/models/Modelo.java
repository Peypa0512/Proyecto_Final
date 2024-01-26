package com.psr.models;

import jakarta.persistence.*;

@Entity

public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "modelo_id")
    private int modelId;
    @Column(name="modelo")
    private String model;

    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;

    public Modelo() {
    }

    public Modelo(String model, Marca marca) {

        this.model = model;
        this.marca = marca;
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

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Modelo [modelId=" + modelId +", model=" + model + "marca= " + marca + "]";
    }
}
