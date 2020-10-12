package com.epam.marketplace.models;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class User {

    private int id;

    @Pattern(regexp = "^[a-zA-Z ]{1,30}$")
    private String name;

    @Pattern(regexp = "^[a-zA-Z ]{1,30}$")
    private String surname;

    @Positive
    private int credentialsId;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getCredentialsId() {
        return credentialsId;
    }

    public void setCredentialsId(int credentialsId) {
        this.credentialsId = credentialsId;
    }
}
