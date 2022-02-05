package com.projekt.projekt.Controllers;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.projekt.projekt.MovieInfo;
import com.projekt.projekt.Receivers.NewBuildingRec;
import com.projekt.projekt.Receivers.RegisterRec;
import com.projekt.projekt.Receivers.emplRec;
import com.projekt.projekt.Receivers.loginData;
import com.projekt.projekt.Receivers.movieRec;
import com.projekt.projekt.Receivers.newSalaRec;
import com.projekt.projekt.Receivers.reservationRec;
import com.projekt.projekt.Receivers.userRec;
import com.projekt.projekt.databases.SeanceDB;
import com.projekt.projekt.tables.Building;
import com.projekt.projekt.tables.Employee;
import com.projekt.projekt.tables.Reservation;
import com.projekt.projekt.tables.Room;
import com.projekt.projekt.tables.Seance;
import com.projekt.projekt.tables.User;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Exceptions.IncorrectPlaceException;


@Controller
public class WebController{
    @Autowired
    DatabaseController db;
    @Autowired
    SeanceDB seanceDB;
    //------------- Public controllers ---------------
    //------------------------------------------------
    @GetMapping("/")
    public String index(Model model){
        List<Seance> films = db.getWeeklySeances();
        List<Entry<Seance, Entry<String, String>>> seances = new ArrayList<>();
        for (Seance seance : films) {
            MovieInfo mi = new MovieInfo(seance.getNazwa_filmu());
            Map.Entry<String, String> plotPoster = new AbstractMap.SimpleEntry<String, String>(mi.getPlot(), mi.getImg());
            Map.Entry<Seance, Entry<String, String>> entry = new AbstractMap.SimpleEntry<Seance, Entry<String, String>>(seance, plotPoster);
            seances.add(entry);
        }
        model.addAttribute("loginRec", new loginData());
        model.addAttribute("regRec", new RegisterRec());
        model.addAttribute("WeeklySeances", seances);
        return "start";
    }
    @GetMapping("/repertuar")
    public String repertuar(Model model){
        model.addAttribute("loginRec", new loginData());
        model.addAttribute("regRec", new RegisterRec());
        List<Seance> films = db.getWeeklySeances();
        List<Entry<Seance, Entry<String, String>>> seances = new ArrayList<>();
        for (Seance seance : films) {
            MovieInfo mi = new MovieInfo(seance.getNazwa_filmu());
            Map.Entry<String, String> plotPoster = new AbstractMap.SimpleEntry<String, String>(mi.getPlot(), mi.getImg());
            Map.Entry<Seance, Entry<String, String>> entry = new AbstractMap.SimpleEntry<Seance, Entry<String, String>>(seance, plotPoster);
            
            seances.add(entry);
        }
        model.addAttribute("WeeklySeances", seances);


        List<Seance> film = db.getNextWeekSeances();
        List<Entry<Seance, Entry<String, String>>> seancess = new ArrayList<>();
        for (Seance seance : film) {
            MovieInfo mi = new MovieInfo(seance.getNazwa_filmu());
            Map.Entry<String, String> plotPoster = new AbstractMap.SimpleEntry<String, String>(mi.getPlot(), mi.getImg());
            Map.Entry<Seance, Entry<String, String>> entry = new AbstractMap.SimpleEntry<Seance, Entry<String, String>>(seance, plotPoster);
            seancess.add(entry);
        }
        model.addAttribute("nextWeekSeances", seancess);

        return "repertuar";
    }
    //--------------- User controllers ---------------
    //------------------------------------------------
    @GetMapping("/zaloguj")
    public String zaloguj(Model model){
        List<Seance> films = db.getWeeklySeances();
        List<Entry<Seance, Entry<String, String>>> seances = new ArrayList<>();
        for (Seance seance : films) {
            MovieInfo mi = new MovieInfo(seance.getNazwa_filmu());
            Map.Entry<String, String> plotPoster = new AbstractMap.SimpleEntry<String, String>(mi.getPlot(), mi.getImg());
            Map.Entry<Seance, Entry<String, String>> entry = new AbstractMap.SimpleEntry<Seance, Entry<String, String>>(seance, plotPoster);
            seances.add(entry);
        }
        model.addAttribute("loginRec", new loginData());
        model.addAttribute("regRec", new RegisterRec());
        model.addAttribute("WeeklySeances", seances);
        return "redirect:/";
    }
    @PostMapping("/register")
    public String register(Model model, RegisterRec rec){

        User newUser = new User(db.getLastUserID()+1, rec.getLogin(), rec.getHasło(), rec.getEmail(), "User");
        try{
            db.addNewUser(newUser);
            return "redirect:/"; 
        }catch(IncorrectPlaceException e){
            model.addAttribute("errormessage", e);
            return "redirect:/";
        }     
    }
    @GetMapping("/panel")
    public String panel(Model model, Authentication auth){
        
        List<Reservation> allUserReservations = db.getAllReservationsForUsername(auth.getName());
        
        List<Entry<Reservation, Integer>> allFixedUserReservations = new ArrayList<>();
        for (Reservation reservation : allUserReservations) {
            Map.Entry<Reservation, Integer> entry = new AbstractMap.SimpleEntry<Reservation, Integer>(reservation, reservation.getRząd());
            allFixedUserReservations.add(entry);
        }
        model.addAttribute("user", db.getUserByLogin(auth.getName()));
        model.addAttribute("receiver", new reservationRec());
        model.addAttribute("reservations", allFixedUserReservations);
        
        return "panel";
    }   
    //----------- Reservation controllers ------------
    //------------------------------------------------
    @GetMapping("/reserve")
    public String reserve(Model model, @RequestParam(name="movie") int ID){
        model.addAttribute("loginRec", new loginData());
        model.addAttribute("seanceId", ID);
        Seance seance = db.getSeanceByID(ID);

        Map<Integer, Boolean> seats = new HashMap<Integer, Boolean>();
        List<Reservation> rezerwacje = db.getAllReservationsForSeance(seance);
        List<Integer> takenSeats = new ArrayList<>();
        for (Reservation reservation : rezerwacje) {
            takenSeats.add(10*(reservation.getRząd()-1) + reservation.getSiedzenie());
        }

        for(int i = 1 ; i<=seance.getSala().getIl_siedzen(); i++){
            if(takenSeats.contains(i)){
                seats.put(i, true);
            }else{
                seats.put(i, false);
            }
        }
        model.addAttribute("reservationRec", new reservationRec());
        model.addAttribute("movie", seance);
        model.addAttribute("siedzenie", seats);
       
//10*rząd+siedzenie-10 = id siedzenia
        return "reservation";
    }
    @PostMapping("/reserve")
    public String reserve(Model model, reservationRec rec){
        int row = 1, seat = rec.getSeat()%10;
        for(int i = rec.getSeat(); i > 10 ; i-=10){
            System.out.println(i);
            row++;
        }
        System.out.println(rec.getUsr() + rec.getSeat() + rec.getSeance());

        User user = db.getUserByLogin(rec.getUsr());
        Seance seance = db.getSeanceByID(Integer.valueOf(rec.getSeance()));
        Reservation res = new Reservation(db.getLastReservationId()+1,user, seance, row, seat, false);
        System.out.print(db.getUserByLogin(rec.getUsr()).getID() + " " + row + " "+ seat);
        try{
            db.addReservation(res);
        }catch(IncorrectPlaceException e){
            e.printStackTrace();
        }
    
        
        return "redirect:/panel"; 
        
        
        
    }
    
    @PostMapping("/reserve/delete")
    public String delRes(Model model, reservationRec rec){
        
        Reservation res = db.reservationsDb.getById(rec.getId());
        
        db.reservationsDb.delete(res);
        
        model.addAttribute("receiver", new reservationRec());
        return "/panel";
    }

    //------------- Staff GET controllers ----------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------
    @GetMapping("/workerpanel")
    public String staff(Model model){
    //============================================================================
    List<Seance> movies = new ArrayList<Seance>();
    List<Room> rooms = db.roomDB.findAll();
    List<Reservation> listOfUnpaidReservations = db.getAllUpnaidReservations();
    List<Seance> films  = db.getWeeklySeances();
    List<Seance> film   = db.getNextWeekSeances();
    for (Seance seance : films){ movies.add(seance); }
    for (Seance seance : film) { movies.add(seance); }
       
    model.addAttribute("sale", rooms );
    model.addAttribute("nextSeances", movies);    
    model.addAttribute("rec", new reservationRec());
    model.addAttribute("movieRec", new movieRec());
    model.addAttribute("NotPaid", listOfUnpaidReservations);
    return "staff";
    }
    @GetMapping("/workerpanel/manager")
    public String staffMan(Model model){

        List<List<String>> sprzedane = db.seanceDB.findByQuery2();
        List<List<String>> soon = db.seanceDB.findByQuery3();
        List<List<String>> wypłaty = db.employeeDB.raportData();
        List<List<String>> stats = db.employeeDB.raportData2();
        List<Building> budynki = db.buildingDb.findAll();
        model.addAttribute("newSal", new newSalaRec());     //        
        model.addAttribute("buds", budynki);                //Dane do formularzy
        model.addAttribute("newEmpl", new emplRec());       //
        model.addAttribute("budRec", new NewBuildingRec()); //  
        model.addAttribute("usRec", new userRec());         //
        model.addAttribute("stats", stats);     //
        model.addAttribute("soon", soon);       //Dane raportów
        model.addAttribute("wyplaty", wypłaty); //    
        model.addAttribute("sold", sprzedane);  //    
    //============================================================================
        List<Seance> movies = new ArrayList<Seance>();
        List<Room> rooms = db.roomDB.findAll();
        List<Reservation> listOfUnpaidReservations = db.getAllUpnaidReservations();
        List<Seance> films  = db.getWeeklySeances();
        List<Seance> film   = db.getNextWeekSeances();
        for (Seance seance : films){ movies.add(seance); }
        for (Seance seance : film) { movies.add(seance); }
           
        model.addAttribute("sale", rooms );                     //
        model.addAttribute("nextSeances", movies);              //    
        model.addAttribute("rec", new reservationRec());        // Dane formularzy        
        model.addAttribute("movieRec", new movieRec());         //    
        model.addAttribute("NotPaid", listOfUnpaidReservations);//                

        return "manager";
    }
    @GetMapping("/workerpanel/administrator")
    public String staffAdm(Model model){
        
        List<List<String>> sprzedane = db.seanceDB.findByQuery2();
        List<List<String>> soon = db.seanceDB.findByQuery3();
        List<List<String>> wypłaty = db.employeeDB.raportData();
        List<List<String>> stats = db.employeeDB.raportData2();
        List<Building> budynki = db.buildingDb.findAll();
        model.addAttribute("newSal", new newSalaRec());     //        
        model.addAttribute("buds", budynki);                //Dane do formularzy
        model.addAttribute("newEmpl", new emplRec());       //
        model.addAttribute("budRec", new NewBuildingRec()); //  
        model.addAttribute("usRec", new userRec());         //
        model.addAttribute("stats", stats);     //
        model.addAttribute("soon", soon);       //Dane raportów
        model.addAttribute("wyplaty", wypłaty); //    
        model.addAttribute("sold", sprzedane);  //    
    //============================================================================
        List<Seance> movies = new ArrayList<Seance>();
        List<Room> rooms = db.roomDB.findAll();
        List<Reservation> listOfUnpaidReservations = db.getAllUpnaidReservations();
        List<Seance> films  = db.getWeeklySeances();
        List<Seance> film   = db.getNextWeekSeances();
        for (Seance seance : films){ movies.add(seance); }
        for (Seance seance : film) { movies.add(seance); }
           
        model.addAttribute("sale", rooms );                     //
        model.addAttribute("nextSeances", movies);              //    
        model.addAttribute("rec", new reservationRec());        // Dane formularzy        
        model.addAttribute("movieRec", new movieRec());         //    
        model.addAttribute("NotPaid", listOfUnpaidReservations);//  
        return "admin";
    }

    //------------ Staff POST Controllers ----------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------
    @PostMapping("/reservepaid")
    public String reservePaid(Model model, reservationRec rec){
        
        Reservation res = db.reservationsDb.getById(rec.getId());
        res.setPaid(true);
        db.reservationsDb.save(res);


        return "redirect:/workerpanel";    
           
    }
    @PostMapping("/markformovie")
    public String markForMovie(Model model, movieRec rec, Authentication auth){
        Seance movie = db.getSeanceByID(rec.getId());
        Set<Employee> allEmployeesForSeance = movie.getPracownik();
        User currUsr = db.getUserByLogin(auth.getName());
        Employee currEmpl = db.employeeDB.getByKonto(currUsr);
        allEmployeesForSeance.add(currEmpl);
        movie.setPracownik(allEmployeesForSeance);

        return "redirect:/workerpanel";    
    }
    @PostMapping("/addmovie")
    public String addmovie(Model model, movieRec rec){
        String data = rec.getData();
        String[] dataElements = data.split("-");
        data = dataElements[2] + "/" + dataElements[1] + "/" + dataElements[0]; 
        Seance newMovie = new Seance(db.seanceDB.count()+1, data, rec.getNazwa_filmu(),db.roomDB.getById(rec.getSala()));
        db.addNewSeance(newMovie);

        return "redirect:/workerpanel"; 
    }
    @PostMapping("/addempl")
    public String addEmployee(Model model, emplRec rec){

        User konto = db.userDB.findFirstByLogin(rec.getKonto());
        try{
            konto.setTyp_konta("Employee");
            db.userDB.save(konto);
            Employee newEmployee = new Employee(db.employeeDB.count()+1, rec.getImie(), rec.getNazwisko(), konto);
    
            db.employeeDB.save(newEmployee);
            return "redirect:/workerpanel/manager"; 
        }catch(NullPointerException e){
            return "redirect:/workerpanel/manager"; 
        }  
    }

    @PostMapping("/newbuilding")
    public String addbuild(Model model, NewBuildingRec rec){
        int id = db.buildingDb.findFirstByOrderByIDDesc().getID();
        Building newBud = new Building(id+1, rec.getNazwa(), rec.getMiasto(), rec.getUlica(), rec.getNr_bud());
        db.buildingDb.save(newBud);
        return "redirect:/workerpanel/manager"; 
    }

    @PostMapping("/newroom")
    public String addroom(Model model, newSalaRec rec){
        Building bud = db.buildingDb.getById(rec.getBudynek());
        int id = db.roomDB.findFirstByOrderByIDDesc().getID();
        int nr_sali = 0;
        List<Room> sale = db.roomDB.findAllByBudynek(bud);
        for (Room room : sale) {
            if(room.getNr_sali() > nr_sali) nr_sali = room.getNr_sali();
        }
        try{
            Room newBud = new Room(id+1, nr_sali+1, rec.getIl_siedzen(), bud );
            db.roomDB.save(newBud);
        }catch(NullPointerException e){} 

        return "redirect:/workerpanel/manager"; 

    }
    @PostMapping("/promote")
    public String promote(Model model, userRec rec){
        System.out.print("---");
        User user = db.userDB.findFirstByLogin(rec.getLogin());
        user.setTyp_konta("Manager");
        db.userDB.save(user);
        return "redirect:/workerpanel/manager"; 

    }
    @PostMapping("/fire")
    public String fire(Model model, userRec rec){
        
        User user = db.userDB.findFirstByLogin(rec.getLogin());
        user.setTyp_konta("User");
        db.userDB.save(user);
        return "redirect:/workerpanel/manager"; 

    }
} 
