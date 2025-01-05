package org.example.MODELS;

import java.sql.Time;
import java.util.Date;

public class Rendezvous {
    
    private int id_rdv;
    private Date date;
    private java.sql.Time heure;
    private int Patient_id;
    private int Medecin_id;

    public Rendezvous(Date date, java.sql.Time heure, int Patient_id, int Medecin_id) {
        this.date = date;
        this.heure = heure;
        this.Patient_id = Patient_id;
        this.Medecin_id = Medecin_id;
    }

    public Rendezvous(int id_rdv, Date date, Time heure, int Patient_id, int Medecin_id) {
        this.id_rdv = id_rdv;
        this.date = date;
        this.heure = heure;
        this.Patient_id = Patient_id;
        this.Medecin_id = Medecin_id;
    }

    // Getters and Setters
    public int getId_rdv() {
        return id_rdv;
    }

    public void setId_rdv(int id_rdv) {
        this.id_rdv = id_rdv;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public java.sql.Time getHeure() {
        return heure;
    }

    public void setHeure(java.sql.Time heure) {
        this.heure = heure;
    }

    public int getPatient_id() {
        return Patient_id;
    }

    public void setPatient_id(int idPatient) {
        this.Patient_id = idPatient;
    }

    public int getMedecin_id() {
        return Medecin_id;
    }

    public void setMedecin_id(int idMedecin) {
        this.Medecin_id = idMedecin;
    }

}