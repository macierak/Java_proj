package com.projekt.projekt.Receivers;


public class reservationRec {
    int id;
    int seance;
    int seat;
    String usr;
    
    public int getId() {
        return id;
    }   
    public void setId(int id) {
        this.id = id;
    }    
    public int getSeance() {
        return seance;
    }
    public int getSeat() {
        return seat;
    }
    public String getUsr() {
        return usr;
    }
    
    public void setSeance(int seance) {
        this.seance = seance;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }public void setUsr(String usr) {
        this.usr = usr;
    }
}
