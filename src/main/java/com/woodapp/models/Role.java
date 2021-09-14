package com.woodapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Enumerated(EnumType.STRING)
//    @Column(length = 20)
    private String name;

//    @OneToMany(mappedBy = "roles", cascade = CascadeType.ALL)
//    @JoinColumn(name = "id")
//    private Set<User> users = new HashSet<>();
//    public int hashCode() {
//        return getClass().hashCode();
//    }

}
