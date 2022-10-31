package com.example.Project;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/admin")
    public String getpeople(){
        return "adminHome";
    }
    @GetMapping("/categories")
    public String getcat(){
        return "categories";
    }


}