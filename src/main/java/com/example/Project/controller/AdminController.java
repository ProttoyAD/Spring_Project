package com.example.Project.controller;

import com.example.Project.dto.ResidenceDTO;
import com.example.Project.model.Residence;
import com.example.Project.model.RoomType;
import com.example.Project.service.RoomTypeService;
import com.example.Project.service.ResidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {

    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/residenceImages";

    @Autowired
    RoomTypeService roomTypeService;
    @Autowired
    ResidenceService residenceService;

    @GetMapping("/admin")
    public String adminHome() {
        return "adminHome";
    }

    @GetMapping("/admin/roomtypes")
    public String getRoomType(Model model) {
        model.addAttribute("roomtypes", roomTypeService.getAllRoomTypes());
        return "roomtypes";
    }

    @GetMapping("/admin/roomtypes/add")
    public String RoomTypeAdd(Model model) {
        model.addAttribute("roomtype", new RoomType());
        return "roomTypesAdd";
    }

    @PostMapping("/admin/roomtypes/add")
    public String postRoomTypeAdd(@ModelAttribute("roomtype") RoomType roomType) {
        roomTypeService.addRoomType(roomType);
        return "redirect:/admin/roomtypes";
    }

    @GetMapping("/admin/roomtypes/delete/{id}")
    public String deleteRoomType(@PathVariable int id) {
        roomTypeService.removeRoomTypeById(id);
        return "redirect:/admin/roomtypes";
    }

    @GetMapping("/admin/roomtypes/update/{id}")
    public String updateRoomType(@PathVariable int id, Model model) {
        Optional<RoomType> category = roomTypeService.getRoomTypeById(id);
        if (category.isPresent()) {
            model.addAttribute("roomtype", category.get());
            return "roomTypesAdd";
        } else
            return "404";
    }

    //residence section
    @GetMapping("/admin/residences")
    public String residence(Model model) {
        model.addAttribute("residences", residenceService.getAllResidences());
        return "residences";
    }

    @GetMapping("/admin/residences/add")
    public String productAddGet(Model model) {
        model.addAttribute("residenceDTO", new ResidenceDTO());
        model.addAttribute("roomtypes", roomTypeService.getAllRoomTypes());
        return "residenceAdd";
    }
    @PostMapping("/admin/residences/add")
    public String residenceAddPost(@ModelAttribute("residenceDTO")ResidenceDTO residenceDTO, @RequestParam("residenceImage")MultipartFile file,
                                 @RequestParam("imgName")String imgName) throws IOException {
        Residence residence = new Residence();
        residence.setId(residenceDTO.getId());
        residence.setName(residenceDTO.getName());
        residence.setRoomType(roomTypeService.getRoomTypeById(residenceDTO.getRoomtypeId()).get());
        residence.setPrice(residenceDTO.getPrice());
        residence.setDistance(residenceDTO.getDistance());
        residence.setDescription(residence.getDescription());
        String imageUUID;
        if (!file.isEmpty()){
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());

        } else {
            imageUUID = imgName;

        }
        residence.setImageName(imageUUID);
        residenceService.addResidence(residence);



        return "redirect:/admin/residences";
    }
    @GetMapping("/admin/residence/delete/{id}")
    public String deleteProduct(@PathVariable long id){
        residenceService.removeResidenceById(id);
        return "redirect:/admin/residences";
    }
    @GetMapping("/admin/residence/update/{id}")
    public String updateProductGet(@PathVariable long id, Model model){
        Residence residence =residenceService.getResidenceById(id).get();
        ResidenceDTO residenceDTO = new ResidenceDTO();
        residenceDTO.setId(residence.getId());
        residenceDTO.setName(residence.getName());
        residenceDTO.setRoomtypeId((residence.getRoomType().getId()));
        residenceDTO.setPrice(residence.getPrice());
        residenceDTO.setDistance((residence.getDistance()));
        residenceDTO.setDescription(residence.getDescription());
        residenceDTO.setImageName(residence.getImageName());

        model.addAttribute("roomtypes", roomTypeService.getAllRoomTypes());
        model.addAttribute("residenceDTO", residenceDTO);

        return "residenceAdd";
    }


}

