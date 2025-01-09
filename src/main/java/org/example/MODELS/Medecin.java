package org.example.MODELS;

import java.time.LocalDate;

public class Medecin extends User {
    private String password;
    private int Medecin_id;


    public Medecin(String CIN, String nom, String prenom, LocalDate date_naissance, String adresse, String telephone, String email, String sexe, String password) {
        super(CIN, nom, prenom, date_naissance, adresse, telephone, email, sexe);
        this.password = password;
    }

    public Medecin(int medecin_id, String CIN, String nom, String prenom, LocalDate date_naissance, String adresse, String telephone, String email, String sexe,  String password) {
        super(CIN, nom, prenom, date_naissance, adresse, telephone, email, sexe);
        Medecin_id = medecin_id;
        this.password = password;
    }




    // Getters and Setters
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMedecin_id() {
        return Medecin_id;
    }

    public void setMedecin_id(int Medecin_id) {
        this.Medecin_id = Medecin_id;
    }
    //toString
    @Override
    public String toString() {
        return "Medecin [CIN=" + CIN + ", adresse=" + adresse + ", date_naissance=" + date_naissance + ", email=" + email
                + ", nom=" + nom + ", password=" + password + ", prenom=" + prenom + ", sexe=" + sexe + ", telephone=" + telephone + "]";
    }
}
