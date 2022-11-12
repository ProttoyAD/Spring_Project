
package com.example.Project.controller;

import com.example.Project.model.Residence;
import com.example.Project.global.GlobalData;
import com.example.Project.service.ResidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookmarkPageController {
    @Autowired
    ResidenceService residenceService;
    @GetMapping("/bookmark/{id}")
    public String addToCart(@PathVariable int id){
        GlobalData.bookmarkedSeats.add(residenceService.getResidenceById(id).get());
        return "redirect:/homepage";
    }

    @GetMapping("/bookmarkedSeats")
    public String cartGet(Model model){
        model.addAttribute("seatsCount", GlobalData.bookmarkedSeats.size());
        model.addAttribute("total",GlobalData.bookmarkedSeats.stream().mapToDouble(Residence::getPrice).sum());
        model.addAttribute("bookmarkedSeats", GlobalData.bookmarkedSeats);
        return "bookmarkedSeats";

    }
    @GetMapping("/bookmarkedSeats/removeSeat/{index}")
    public String cartItemRemove(@PathVariable int index) {
        GlobalData.bookmarkedSeats.remove(index);
        return "redirect:/bookmarkedSeats";
    }
    @GetMapping("/checkout")
    public String checkout(Model model){
        model.addAttribute("total",GlobalData.bookmarkedSeats.stream().mapToDouble(Residence::getPrice).sum());
        return "checkout";

    }
}