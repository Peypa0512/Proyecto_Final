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

    public Modelo() {
    }

    public Modelo(String model) {

        this.model = model;
    }

    public int getmodelId() {
        return modelId;
    }

    public void setmodelId(int modelId) {
        this.modelId = modelId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Modelo [modelId=" + modelId +", model=" + model +  "]";
    }
}
