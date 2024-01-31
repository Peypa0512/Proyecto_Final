package com.psr.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Propietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "propietario_id")
    private int ownerId;
    @Column(name="nombre")
    private String name;

    private String dni;
    @Column(name="ciudad")
    private String city;
    @Column(name="telefono")
    private String phone;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Coche> car;
    public Propietario() {
    }

    public Propietario(String name, String dni, String city, String phone) {
        this.name = name;
        this.dni = dni;
        this.city = city;
        this.phone = phone;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Coche> getCar() {
        return car;
    }

    public void setCar(List<Coche> car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Propietario{" +
                "ownerId=" + ownerId +
                ", name='" + name + '\'' +
                ", dni='" + dni + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", car=" + car +
                '}';
    }
}
