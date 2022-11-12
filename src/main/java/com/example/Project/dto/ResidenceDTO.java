package com.example.Project.dto;

import com.example.Project.model.RoomType;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class ResidenceDTO {
    private Long id;
    private String name;

    private int roomtypeId;
    private int price;
    private double distance;
    private String description;
    private String imageName;
}