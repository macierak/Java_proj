package com.projekt.projekt.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.projekt.projekt.databases.BuildingDB;
import com.projekt.projekt.databases.EmployeeDB;
import com.projekt.projekt.databases.PersonDB;
import com.projekt.projekt.databases.ReservationDB;
import com.projekt.projekt.databases.RoomDB;
import com.projekt.projekt.databases.SeanceDB;
import com.projekt.projekt.databases.UserDB;
import com.projekt.projekt.tables.Reservation;
import com.projekt.projekt.tables.Seance;
import com.projekt.projekt.tables.User;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import Exceptions.IncorrectPlaceException;


public class DatabaseController {
    int attempt = 0;
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

    public void addReservation(Reservation res) throws IncorrectPlaceException{       
        List<Reservation> allReservarions = reservationsDb.findAllBySeans(res.getSeans());
        for (Reservation reservation : allReservarions) {
            if(res.getRząd() == reservation.getRząd() && res.getSiedzenie() == reservation.getSiedzenie()){
                throw new IncorrectPlaceException("To miejsce jest już zajęte");
            }
        }
        try{
            reservationsDb.save(res);
        }catch(Exception e){
            attempt++; 
            System.out.println("------------------------"+ attempt +"-----------------------------");
            this.addReservation(res);
        } 
    }

    public void addNewUser(User user) throws IncorrectPlaceException{
        List<User> allUsers = userDB.findAll();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setHasło(encoder.encode(user.getHasło()));
        for (User user2 : allUsers) {
            if(user.getLogin() == user2.getLogin()){
                throw new IncorrectPlaceException("Login zajęty");
            }
        }
        try{
            userDB.saveAndFlush(user);
        }catch(Exception e){
            attempt++;
            System.out.println("------------------------"+ attempt +"-----------------------------");
            this.addNewUser(user);
        }
        
    }
    public User getUserByLogin(String login){
        return userDB.findFirstByLogin(login);
    }
    public int getLastUserID(){
        return userDB.findAll().get(userDB.findAll().size()-1).getID();
    }
    public int getLastReservationId(){
        List<Reservation> reservations = reservationsDb.findAll();
        return reservations.get(reservations.size()-1).get_ID();
    }
    public List<Reservation> getAllReservationsForSeance(Seance seance){
        return reservationsDb.findAllBySeans(seance);
    }
    public List<Reservation> getAllReservationsForUsername(String username){
        User user = userDB.findFirstByLogin(username);
        return reservationsDb.findAllByKonto(user);
    }
    public List<Seance> getWeeklySeances(){
        List<Seance> seances = seanceDB.findByQuery();
        List<Seance> parsedResults = new ArrayList<>();
        for (Seance seance : seances) {
            
            if(seance.getNazwa_filmu().contains("(")){
                seance.setNazwa_filmu(seance.getNazwa_filmu().substring(0, seance.getNazwa_filmu().indexOf("(")-1)); 
            }
            if (seance.getNazwa_filmu().contains(",")){
                seance.setNazwa_filmu(seance.getNazwa_filmu().split(",")[1] + " " + seance.getNazwa_filmu().split(",")[0]);
            }
            parsedResults.add(seance);
        }
        return parsedResults;
    }
    public List<Seance> getNextWeekSeances(){
        List<Seance> seances = seanceDB.findByQuery1();
        List<Seance> parsedResults = new ArrayList<>();
        for (Seance seance : seances) {
            
            if(seance.getNazwa_filmu().contains("(")){
                seance.setNazwa_filmu(seance.getNazwa_filmu().substring(0, seance.getNazwa_filmu().indexOf("(")-1)); 
            }
            if (seance.getNazwa_filmu().contains(",")){
                seance.setNazwa_filmu(seance.getNazwa_filmu().split(",")[1] + " " + seance.getNazwa_filmu().split(",")[0]);
            }
            parsedResults.add(seance);
        }
        return parsedResults;
    }
    public User getUserByID(int id){
        User usr = userDB.findFirstByID(id);
        return usr;
    }
    public Seance getSeanceByID(int id){
        Seance seance = seanceDB.getById(id);
        return seance;
    }

    public List<Reservation> getAllUpnaidReservations() {
        
        return reservationsDb.findAllByPaid(false);
    }
    public void addNewSeance(Seance seance){
        try{
            seanceDB.save(seance);
        }catch(Exception e){
            attempt++;
            System.out.println("------------------------"+ attempt +"-----------------------------");
            this.addNewSeance(seance);
        }
    }
}
