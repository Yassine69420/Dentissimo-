package org.example.MODELS;

import java.time.LocalDate;

public class Secretaire extends User {
    private int id_sec;
    private String password ;

    public Secretaire( String CIN, String nom, String prenom, LocalDate date_naissance, String adresse, String telephone, String email, String sexe, String password) {
        super(CIN, nom, prenom, date_naissance, adresse, telephone, email, sexe);
        this.password = password;
    }
    // Getters and Setters
    public int getId_sec() {return id_sec;}
    public void setId_sec(int id_sec) {this.id_sec = id_sec;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
}
