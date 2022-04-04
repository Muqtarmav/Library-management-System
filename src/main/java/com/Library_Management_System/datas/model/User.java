package com.Library_Management_System.datas.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class User {
//
//    public User(String firstName, String lastName, String age, String email, String UserName) {
//
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String age;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String mobile;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roleList;
//
//    public User(String hwje, String mdm, String elel, String emle, String elle) {
//    }
}