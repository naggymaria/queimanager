package com.example.carmanager.services.model;

import com.google.gson.annotations.SerializedName;

public class Person {
    @SerializedName("personId")
    public Long personId;
    @SerializedName("personName")
    public String personName;
    @SerializedName("code")
    public String code;
    @SerializedName("admin")
    public boolean admin;

    public Person() {
    }

    public Person(Long personId, String personName, String code, boolean admin) {
        this.personId = personId;
        this.personName = personName;
        this.code = code;
        this.admin = admin;
    }
}
