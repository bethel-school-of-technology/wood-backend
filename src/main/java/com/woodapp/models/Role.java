package com.woodapp.models;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole roleName;

//    @OneToMany(mappedBy = "roles", cascade = CascadeType.ALL)
//    @JoinColumn(name = "id")
//    private Set<User> users = new HashSet<>();
//    public int hashCode() {
//        return getClass().hashCode();
//    }

    public Role() {
    }

    public Role(ERole roleName) {
        
        this.roleName = roleName;
    }
    public Integer getId() {
        
        return id;
    }
    public void setId(Integer id) {
        
        this.id = id;
    }
    public ERole getRoleName() {
        
        return roleName;
    }
    public void setRoleName(ERole roleName) {
        
        this.roleName = roleName;
    }

    public Optional<Object> stream() {
        return null;
    }
}
