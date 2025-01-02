package org.example.MODELS;

import java.util.Date;

public class Medecin extends User {
    private String password;

    public Medecin(String CIN, String nom, String prenom, Date date_naissance, String adresse, String telephone, String email, String sexe, String password) {
        super(CIN, nom, prenom, date_naissance, adresse, telephone, email, sexe);
        this.password = password;
    }
    // Getters and Setters
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
