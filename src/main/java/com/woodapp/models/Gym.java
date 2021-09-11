package com.woodapp.models;

import lombok.*;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "gyms")
public class Gym {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="gym_id", nullable = false)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "phone_number", nullable = false)
    private Integer phoneNumber;

    @Column(name = "street_address", nullable = false)
    private String streetAddress;

    @Column(nullable=false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(name = "zip_code", nullable = false)
    private Integer zipCode;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "gym_id")
    private List<User> users;

   // @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
   // @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    //private User user;

}
