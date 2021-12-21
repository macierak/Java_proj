package com.projekt.projekt.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.projekt.projekt.MovieInfo;
import com.projekt.projekt.databases.BuildingDB;
import com.projekt.projekt.databases.EmployeeDB;
import com.projekt.projekt.databases.PersonDB;
import com.projekt.projekt.databases.ReservationDB;
import com.projekt.projekt.databases.RoomDB;
import com.projekt.projekt.databases.SeanceDB;
import com.projekt.projekt.databases.UserDB;

import com.projekt.projekt.tables.User;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class DatabaseController {
    
    @Autowired
    BuildingDB buildingDb;
    @Autowired
    EmployeeDB employeeDB;
    @Autowired
    PersonDB personDB;
    @Autowired
    ReservationDB reservationsDb;
    @Autowired
    RoomDB roomDB;
    @Autowired
    SeanceDB seanceDB;
    @Autowired
    UserDB userDB;


    public User login(String login, String haslo){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return userDB.findFirstByLoginAndHasło(login, encoder.encode(haslo));
    }
    public List<String> getThreeSoonestSeances(){
        List<String> seances = seanceDB.findByQuery(PageRequest.of(0, 3));
        List<String> parsedResults = new ArrayList<>();
        for (String string : seances) {
            
            if(string.contains("(")){
                string = string.substring(0, string.indexOf("(")-1);
            }
            if (string.contains(",")){
                string = string.split(",")[1] + " " + string.split(",")[0];
            }
            parsedResults.add(string);
        }
        return parsedResults;
    }
}
