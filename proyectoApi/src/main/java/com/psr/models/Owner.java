package com.psr.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idowner") // id de la tabla
    private int idOwner;
    private String name;
    private String dni;
    private String city;
    private String phone;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Car> car;

    public Owner() {
    }

    public Owner(String name, String dni, String city, String phone) {
        this.name = name;
        this.dni = dni;
        this.city = city;
        this.phone = phone;
    }

    public int getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
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

    public List<Car> getCar() {
        return car;
    }

    public void setCar(List<Car> car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Propietario{" +
                "idowner=" + idOwner +
                ", name='" + name + '\'' +
                ", dni='" + dni + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", car=" + car +
                '}';
    }
}
