package com.example.carmanager.services.model;

import com.google.gson.annotations.SerializedName;

public class Event {
    @SerializedName("eventId")
    public Long eventId;
    @SerializedName("eventName")
    public String eventName;
    @SerializedName("revenue")
    public double revenue;
    @SerializedName("expenses")
    public double expenses;
    @SerializedName("eventDate")
    public String eventDate;
    @SerializedName("done")
    public boolean done;

    public Event() {
    }

    public Event(Long eventId, String eventName, double revenue, double expenses, String eventDate, boolean done) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.revenue = revenue;
        this.expenses = expenses;
        this.eventDate = eventDate;
        this.done = done;
    }
}
