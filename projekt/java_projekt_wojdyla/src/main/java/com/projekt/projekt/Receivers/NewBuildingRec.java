package com.projekt.projekt.Receivers;

public class NewBuildingRec {
    String Nazwa, Miasto, Ulica, nr_bud;
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
