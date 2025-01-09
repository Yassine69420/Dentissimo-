package org.example.MODELS;


import java.time.LocalDate;

public class Patient extends User {
    private int patient_id;
    private float facture_total;
    private int age;
    private LocalDate date_ajout;





    public Patient(int patient_id, String CIN, String nom, String prenom, LocalDate date_naissance, String adresse, String telephone, String email, String sexe, int age, float facture_total, LocalDate date_ajout) {
        super(CIN, nom, prenom, date_naissance, adresse, telephone, email, sexe);
        this.patient_id = patient_id;
        this.age = age;
        this.facture_total = facture_total;
        this.date_ajout = date_ajout;
    }
    public Patient(String CIN, String nom, String prenom, LocalDate date_naissance, String adresse, String telephone, String email, String sexe, int age, float facture_total, LocalDate date_ajout) {
        super(CIN, nom, prenom, date_naissance, adresse, telephone, email, sexe);
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

    public LocalDate getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(LocalDate date_ajout) {
        this.date_ajout = date_ajout;
    }

    @Override
    public String toString() {
        return "Patient [CIN=" + CIN + ", adresse=" + adresse + ", age=" + age + ", date_naissance=" + date_naissance + ", date_ajout=" + date_ajout + ", email=" + email
                + ", facture_total=" + facture_total + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", telephone=" + telephone + "]";
    }
}
