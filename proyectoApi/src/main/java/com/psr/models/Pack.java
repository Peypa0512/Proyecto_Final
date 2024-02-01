package com.psr.models;


import jakarta.persistence.*;

@Entity
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idpack")
    private int idPack;
    private String color;
    private String finish;
    private String engine;
    private int cv;

    @Column(name = "car_finish")
    private String carFinish;

    @ManyToOne
    @JoinColumn(name = "idcar", nullable = false)
    private Car car;

    public Pack() {
    }

    public Pack(String color, String finish, String engine, int cv, String carFinish, Car car) {
        this.color = color;
        this.finish = finish;
        this.engine = engine;
        this.cv = cv;
        this.carFinish = carFinish;
        this.car = car;
    }

    public int getIdPack() {
        return idPack;
    }

    public void setIdPack(int idPack) {
        this.idPack = idPack;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Pack{" +
                "idPack=" + idPack +
                ", color='" + color + '\'' +
                ", finish='" + finish + '\'' +
                ", engine='" + engine + '\'' +
                ", cv=" + cv +
                ", carFinish='" + carFinish + '\'' +
                ", car=" + car.getRegistration() +
                '}';
    }
}
