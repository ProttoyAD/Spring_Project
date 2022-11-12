package com.example.Project.controller;

import com.example.Project.global.GlobalData;
import com.example.Project.service.ResidenceService;
import com.example.Project.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @Autowired
    RoomTypeService roomTypeService;
    @Autowired
    ResidenceService residenceService;

    @GetMapping({"/", "/home"})
    public String home(Model model){
        model.addAttribute("seatsCount", GlobalData.bookmarkedSeats.size());
        return "index";

    }
    @GetMapping({"/homepage"})
    public String shop(Model model){
        model.addAttribute("roomtypes", roomTypeService.getAllRoomTypes());
        model.addAttribute("residences", residenceService.getAllResidences());
        model.addAttribute("seatsCount", GlobalData.bookmarkedSeats.size());

        return "homepage";

    }
    @GetMapping({"/homepage/roomtype/{id}"})
    public String shopByCategory(Model model, @PathVariable int id){
        model.addAttribute("roomtypes", roomTypeService.getAllRoomTypes());
        model.addAttribute("residences", residenceService.getAllResidenceByRoomTypeId(id));
        model.addAttribute("seatsCount", GlobalData.bookmarkedSeats.size());

        return "homepage";

    }
    @GetMapping({"/homepage/viewResidence/{id}"})
    public String viewProduct(Model model, @PathVariable int id){
        model.addAttribute("residence", residenceService.getResidenceById(id).get());
        model.addAttribute("seatsCount", GlobalData.bookmarkedSeats.size());


        return "viewResidence";

    }


}