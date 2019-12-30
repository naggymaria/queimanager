package com.example.carmanager.services.model;

import com.google.gson.annotations.SerializedName;

/* Data Transfer Object - Usado para interpretar as respostas vindas do servidor (API)*/
public class Car {
    @SerializedName("carId")
    public Long carId;
    @SerializedName("carName")
    public String carName;
    @SerializedName("code")
    public String code;
    @SerializedName("carYear")
    public Integer carYear;
    @SerializedName("course")
    public String course;
    @SerializedName("amount")
    public double amount;

    public Car() {
    }

    public Car(Long carId, String carName, String code, Integer carYear, String course, double amount) {
        this.carId = carId;
        this.carName = carName;
        this.code = code;
        this.carYear = carYear;
        this.course = course;
        this.amount = amount;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCarYear() {
        return carYear;
    }

    public void setCarYear(Integer carYear) {
        this.carYear = carYear;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
