package com.Library_Management_System.datas.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String StoryName;

}
