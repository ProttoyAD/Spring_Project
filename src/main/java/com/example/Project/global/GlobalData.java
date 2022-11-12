package com.example.Project.global;

import com.example.Project.model.Residence;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Residence> bookmarkedSeats;
    static {
        bookmarkedSeats = new ArrayList<Residence>();
    }
}
