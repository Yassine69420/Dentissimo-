package org.example.MODELS;


import org.example.Repositories.Exceptions.DAOException;

import java.util.Date;

public class Patient extends User {
    private int patient_id;
    private float facture_total;
    private int age;
    private Date date_ajout;



    public Patient(String CIN, String nom, String prenom, Date date_naissance, String adresse, String telephone,
                   String email, String sexe , int age ) throws DAOException {
        super(CIN, nom, prenom, date_naissance, adresse, telephone, email, sexe);

        this.age = age;
        this.date_ajout = new Date(); // Assigning the current date
    }

    public Patient(String CIN, String nom, String prenom, Date date_naissance, String adresse, String telephone, String email, String sexe, int patient_id, int age, float facture_total, Date date_ajout) {
        super(CIN, nom, prenom, date_naissance, adresse, telephone, email, sexe);
        this.patient_id = patient_id;
        this.age = age;
        this.facture_total = facture_total;
        this.date_ajout = date_ajout;
    }

    // Getters and Setters
    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public float getFacture_total() {
        return facture_total;
    }

    public void setFacture_total(float facture_total) {
        this.facture_total = facture_total;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }

}
