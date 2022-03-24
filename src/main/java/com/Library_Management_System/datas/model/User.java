package com.Library_Management_System.datas.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {

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
}
