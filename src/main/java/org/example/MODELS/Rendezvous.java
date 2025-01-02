package org.example.MODELS;

import java.util.Date;

class Rendezvous {
    
    private int id_rdv;
    private Date date;
    private java.sql.Time heure;
    private Patient idPatient;
    private Medecin idMedecin;

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

    public Patient getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Patient idPatient) {
        this.idPatient = idPatient;
    }

    public Medecin getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(Medecin idMedecin) {
        this.idMedecin = idMedecin;
    }

}