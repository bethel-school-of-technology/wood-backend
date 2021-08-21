package com.woodapp.woodbackend.model;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private final UUID id;

    @NotNull(message="name cannot be missing or empty")
    @Size(min=2, message="name must not be less than 2 characters")
    private final String name;

    // can't get NotNull nor NotBlank to work

    public User(@JsonProperty("id") UUID id, @JsonProperty("name") String name){
        this.id =id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}