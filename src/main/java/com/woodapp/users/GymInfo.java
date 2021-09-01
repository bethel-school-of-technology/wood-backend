package com.woodapp.users;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor

@Entity
@Table(name = "gym_info")
public class GymInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="gym_info_id", updatable=false)
    private Integer gymInfoId;

    @Column(name = "gym_name", nullable = false)
    private String gymName;

    @Column(name = "phone_number", nullable = false)
    private Integer phoneNumber;

    @Column(name = "street_address", nullable = false)
    private String streetAddress;

    @Column(nullable = false)
    private String state;

    @Column(name = "zip_code", nullable = false)
    private Integer zipCode;

}
