package com.projekt.projekt.Receivers;

public class RegisterRec {
    String login;
    String hasło;
    String email;
    String typ_konta;

    public String getEmail() {
        return email;
    }
    public String getHasło() {
        return hasło;
    }

    public String getLogin() {
        return login;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setHasło(String hasło) {
        this.hasło = hasło;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getTyp_konta() {
        return typ_konta;
    }
    public void setTyp_konta(String typ_konta) {
        this.typ_konta = typ_konta;
    }
}
