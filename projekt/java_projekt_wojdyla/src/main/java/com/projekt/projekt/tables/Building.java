package com.projekt.projekt.tables;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Budynek")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int ID;
    String Nazwa, Miasto, Ulica, nr_bud;

    public int getID() {
        return ID;
    }
    public String getMiasto() {
        return Miasto;
    }
    public String getNazwa() {
        return Nazwa;
    }
    public String getNr_bud() {
        return nr_bud;
    }
    public String getUlica() {
        return Ulica;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public void setMiasto(String miasto) {
        Miasto = miasto;
    }
    public void setNazwa(String nazwa) {
        Nazwa = nazwa;
    }
    public void setNr_bud(String nr_bud) {
        this.nr_bud = nr_bud;
    }
    public void setUlica(String ulica) {
        Ulica = ulica;
    }

   


}
