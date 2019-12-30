package com.example.carmanager.services.interfaces;

import com.example.carmanager.services.model.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PersonInterface {
    @GET("/person/car/{id}/all")
    Call<List<Person>> getAllPeopleByCarId(@Path("id") Long id);

    @DELETE("/person/{id}/remove")
    Call<String> removePersonById(@Path("id") Long id);

    @POST("/person/create?")
    Call<String> createPerson(@Body Person person, @Query("carCode") String carCode);
}
