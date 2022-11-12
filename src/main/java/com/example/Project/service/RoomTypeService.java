package com.example.Project.service;

import com.example.Project.model.RoomType;
import com.example.Project.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomTypeService {
    @Autowired
    RoomTypeRepository roomTypeRepository;

    public List<RoomType> getAllRoomTypes(){
        return roomTypeRepository.findAll();
    }

    public void addRoomType(RoomType category){
        roomTypeRepository.save(category);
    }
    public void removeRoomTypeById(int id){
        roomTypeRepository.deleteById(id); }
    public Optional<RoomType> getRoomTypeById(int id) {
        return roomTypeRepository.findById(id);
    }
}
