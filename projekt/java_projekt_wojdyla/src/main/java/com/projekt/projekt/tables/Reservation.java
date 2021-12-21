package com.projekt.projekt.tables;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

@Entity
@Table(name = "Rezerwacja")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int _ID;

    @ManyToOne
    User konto;
    
    @ManyToOne
    Seance seans;
    
    int rząd;
    int siedzenie;

    
   
    public int get_ID() {
        return _ID;
    }
    public void set_ID(int _ID) {
        this._ID = _ID;
    }
    public User getKonto() {
        return konto;
    }
    public int getRząd() {
        return rząd;
    }
    public Seance getSeans() {
        return seans;
    }
    public int getSiedzenie() {
        return siedzenie;
    }
    public void setKonto(User konto) {
        this.konto = konto;
    }
    public void setRząd(int rząd) {
        this.rząd = rząd;
    }
    public void setSeans(Seance seans) {
        this.seans = seans;
    }
    public void setSiedzenie(int siedzenie) {
        this.siedzenie = siedzenie;
    }
}
