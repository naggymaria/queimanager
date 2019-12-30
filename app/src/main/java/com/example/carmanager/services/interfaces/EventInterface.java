package com.example.carmanager.services.interfaces;

import com.example.carmanager.services.model.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EventInterface {
    @GET("/event/car/{id}/all")
    Call<List<Event>> getAllEventsByCarId(@Path("id") Long id);

    @DELETE("/event/{id}/remove")
    Call<String> removeEventByCarId(@Path("id") Long id);

    @POST("/event/create?")
    Call<String> createEvent(@Body Event event, @Query("carCode") String carCode);
}
