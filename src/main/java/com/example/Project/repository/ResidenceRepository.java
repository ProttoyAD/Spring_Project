package com.example.Project.repository;

import com.example.Project.model.Residence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResidenceRepository extends JpaRepository<Residence, Long>{

    List<Residence> findAllByRoomType_Id(int id);
}
