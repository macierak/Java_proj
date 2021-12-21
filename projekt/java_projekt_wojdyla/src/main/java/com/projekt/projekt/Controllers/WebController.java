package com.projekt.projekt.Controllers;


import java.util.ArrayList;
import java.util.List;


import com.projekt.projekt.MovieInfo;
import com.projekt.projekt.Receivers.loginData;
import com.projekt.projekt.tables.Reservation;
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


    @GetMapping("/main")
    public String index(Model model){

        System.out.println("11");
        List<String> soonestSeances = db.getThreeSoonestSeances(); 

        List<String> moviePosters = new ArrayList<>();
        for (String seance : soonestSeances) {
            
            String link = new MovieInfo(seance).getImg();
            try{
                moviePosters.add((link.substring(1, link.length()-1)));
            }catch(StringIndexOutOfBoundsException e){
                System.out.print(link);
                
            }
            
        }
        System.out.println("13");
        model.addAttribute("moviePoster", moviePosters);
        return "start";
    }


    @GetMapping("/loginpage")
    public String login(Model model){

        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, loginData data){
       
        return "/index";
    }

    @PostMapping("/zarezerwuj")
    public String postReservation(Model model, Reservation reservation){

        return "reservationSuccess";
    }

}
