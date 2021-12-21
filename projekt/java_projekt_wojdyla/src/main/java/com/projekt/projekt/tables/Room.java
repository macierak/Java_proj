package com.projekt.projekt.tables;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GenerationType;

@Entity
@Table(name = "Sala")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int ID;

    @ManyToOne
    Building budynek;

    int nr_sali, il_siedzen;

    public int getIl_siedzen() {
        return il_siedzen;
    }
    public void setIl_siedzen(int il_siedzen) {
        this.il_siedzen = il_siedzen;
    }
    public Building getBudynek() {
        return budynek;
    }
    public int getID() {
        return ID;
    }
    public void setBudynek(Building budynek) {
        this.budynek = budynek;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public int getNr_sali() {
        return nr_sali;
    }
    public void setNr_sali(int nr_sali) {
        this.nr_sali = nr_sali;
    }

}
