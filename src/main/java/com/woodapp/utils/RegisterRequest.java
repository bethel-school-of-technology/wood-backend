package com.woodapp.utils;

import com.woodapp.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

// ***will need to include the rest of the data that we want for the registration request

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Collection<Role> roles = new ArrayList<>();

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private String gender;
    //	(we'll need a placeholder like MM/DD/YY on the form)
    private String birthday;
    private Integer phoneNumber;
    private String streetAddress;
    private String city;
    private String state;
    private Integer zipCode;
    //will need to be a drop-down menu on the form
    private String membershipType;
    private LocalDate signUpDate;

}
