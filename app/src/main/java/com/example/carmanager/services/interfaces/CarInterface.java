package com.example.carmanager.services.interfaces;

import com.example.carmanager.services.model.Car;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CarInterface {
    @GET("/car/{id}")
    Call<Car> getCarById(@Path("id") Long id);

    //adicionar o get by code
    @GET("/car/{code}")
    Call<Car> getCarByCode(@Path("code") String code);

    @POST("/car/create")
    Call<String> createCar(@Body Car car);
}