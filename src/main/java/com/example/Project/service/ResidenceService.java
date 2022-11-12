package com.example.Project.service;

import com.example.Project.model.Residence;
import com.example.Project.repository.ResidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResidenceService {
    @Autowired
    ResidenceRepository residenceRepository;
    public List<Residence> getAllResidences(){
        return residenceRepository.findAll();}
    public void addResidence(Residence residence){

        residenceRepository.save(residence);
    }

    public void removeResidenceById(long id){
        residenceRepository.deleteById(id);
    }
    public Optional<Residence> getResidenceById(long id){
        return residenceRepository.findById(id);
    }
    public List<Residence> getAllResidenceByRoomTypeId(int id){
        return residenceRepository.findAllByRoomType_Id(id);
    }
}
