package com.woodapp.models;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private final UUID id;
    @NotNull(message="name cannot be missing or empty")
    private String name;
    // can't get NotNull nor NotBlank to work

    //will need to add login details like email/username and password later

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

    public void setName(String name) {
        this.name = name;
    }

}