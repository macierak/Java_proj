package com.projekt.projekt.tables;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Pracownik")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int _ID;



    @OneToOne
    User konto;
    String imie;
    String Nazwisko;

    public String getImie() {
        return imie;
    }
    public String getNazwisko() {
        return Nazwisko;
    }

    public int get_ID() {
        return _ID;
    }
    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setKonto(User konto) {
        this.konto = konto;
    }
    public User getKonto() {
        return konto;
    }
    public void setNazwisko(String nazwisko) {
        Nazwisko = nazwisko;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }
}
