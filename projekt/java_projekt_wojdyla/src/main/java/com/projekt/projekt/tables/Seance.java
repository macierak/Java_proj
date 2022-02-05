package com.projekt.projekt.tables;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Seans")
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int ID;

    String data, nazwa_filmu;
    @ManyToOne
    Room sala;

    @ManyToMany
    Set<Employee> Pracownik;

    public Seance(){}

    public Seance(long id, String data, String nazwa, Room sala){
        this.ID = (int) id;
        this.data = data;
        this.nazwa_filmu = nazwa;
        this.sala = sala;
    }

    public String getNazwa_filmu() {
        return nazwa_filmu;
    }
    public void setNazwa_filmu(String nazwa_filmu) {
        this.nazwa_filmu = nazwa_filmu;
    }

    public String getData() {
        return data;
    }
    public int getID() {
        return ID;
    }
    public Set<Employee> getPracownik() {
        return Pracownik;
    }
    public Room getSala() {
        return sala;
    }
    public void setData(String data) {
        this.data = data;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public void setPracownik(Set<Employee> pracownik) {
        Pracownik = pracownik;
    }
    public void setSala(Room sala) {
        this.sala = sala;
    }
}
