package com.psr.models;

import jakarta.persistence.*;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idbrand")
    private int idBrand;
    private String name;

    public Brand() {

    }

    public Brand(String name) {
        this.name = name;
    }

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "brand [idBrand=" + idBrand + ", name=" + name + "]";
    }
}
