package com.psr.models;

import jakarta.persistence.*;

@Entity
@Table(name = "marca")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marca_id")
    private int brandId;
    @Column(name="nombre", nullable = false)
    private String name;

    public Marca() {

    }

    public Marca(String name) {

        this.name = name;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "brand [brandId=" + brandId + ", name=" + name + "]";
    }
}
