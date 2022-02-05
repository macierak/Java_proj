package com.projekt.projekt.Receivers;



public class movieRec {
    
    
    String data, nazwa_filmu;
    
    int sala, id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getData() {
        return data;
    }
    public String getNazwa_filmu() {
        return nazwa_filmu;
    }
    public int getSala() {
        return sala;
    }
    public void setData(String data) {
        this.data = data;
    }
    public void setNazwa_filmu(String nazwa_filmu) {
        this.nazwa_filmu = nazwa_filmu;
    }
    public void setSala(int sala) {
        this.sala = sala;
    }
}
