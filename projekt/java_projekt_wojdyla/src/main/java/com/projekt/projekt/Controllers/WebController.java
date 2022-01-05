package com.projekt.projekt.Controllers;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.projekt.projekt.MovieInfo;
import com.projekt.projekt.Receivers.loginData;
import com.projekt.projekt.tables.Seance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController{
    @Autowired
    DatabaseController db;


    @GetMapping("/")
    public String index(Model model){
        
        return "start";
    }

    @GetMapping("/zaloguj")
    public String zaloguj(Model model){
        model.addAttribute("loginRec", new loginData());
        return "loginpage";
    }

    @GetMapping("/panel")
    public String panel(Model model){

        return "panel";
    }
    @GetMapping("/repertuar")
    public String repertuar(Model model){
        List<Seance> films = db.getWeeklySeances();
        List<Entry<Seance, String>> seances = new ArrayList<>();
        for (Seance seance : films) {
            MovieInfo mi = new MovieInfo(seance.getNazwa_filmu());
            Map.Entry<Seance, String> entry = new AbstractMap.SimpleEntry<Seance, String>(seance, mi.getPlot());
            seances.add(entry);
        }
        model.addAttribute("WeeklySeances", seances);
        return "repertuar";
    }
}
