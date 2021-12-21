package com.projekt.projekt.tables;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "Osoba")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int ID;

    String Imię;
    String Nazwisko;

    @OneToMany
    Set<User> Konto;
    String nr_tel;

    
    public void addUserToPerson(User user){
        Konto.add(user);
    }

    public String getNazwisko() {
        return Nazwisko;
    }
    public int getID() {
        return ID;
    }
    public String getImię() {
        return Imię;
    }   
    public String getNr_tel() {
        return nr_tel;
    }   
    public void setID(int iD) {
        ID = iD;
    }
    public void setImię(String imię) {
        Imię = imię;
    }
    public void setNazwisko(String nazwisko) {
        Nazwisko = nazwisko;
    }
    public void setNr_tel(String nr_tel) {
        this.nr_tel = nr_tel;
    }
    public Set<User> getKonto() {
        return Konto;
    }
    public void setKonto(Set<User> user) {
        Konto = user;
    }
    
}
