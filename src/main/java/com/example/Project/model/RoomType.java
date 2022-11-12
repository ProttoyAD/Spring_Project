package com.example.Project.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="roomtype_id")
    private int id;

    private String name;

}
