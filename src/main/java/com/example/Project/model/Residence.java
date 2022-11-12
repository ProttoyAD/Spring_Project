package com.example.Project.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Residence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomtype_id" ,referencedColumnName = "roomtype_id")
    private RoomType roomType;
    private int price;
    private double distance;
    private String description;
    private String imageName;
}
