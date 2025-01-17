package org.example.MODELS;



import org.example.MODELS.enums.Assurence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Patient extends User {
    private int patient_id;
    private Double facture_total;
    private int age;
    private Assurence assurence;
    private String risque ;
    private LocalDateTime date_ajout ;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Patient(int patient_id, String CIN, String nom, String prenom, LocalDate date_naissance, String adresse, String telephone, String email, String sexe, int age,Assurence assurence, String risque,Double facture_total) {
        super(CIN, nom, prenom, date_naissance, adresse, telephone, email, sexe);
        this.patient_id = patient_id;
        this.age = age;
        this.assurence = assurence ;
        this.risque = risque ;
        this.facture_total = facture_total;
        this.date_ajout =  LocalDateTime.now();
    }
    public Patient(int patient_id, String CIN, String nom, String prenom, LocalDate date_naissance, String adresse, String telephone, String email, String sexe, int age,Assurence assurence, String risque,Double facture_total,LocalDateTime date_ajout) {
        super(CIN, nom, prenom, date_naissance, adresse, telephone, email, sexe);
        this.patient_id = patient_id;
        this.age = age;
        this.assurence = assurence ;
        this.risque = risque ;
        this.facture_total = facture_total;
        this.date_ajout =  date_ajout;
    }
    public Patient(String CIN, String nom, String prenom, LocalDate date_naissance, String adresse, String telephone, String email, String sexe, int age,Assurence assurence,String risque, Double facture_total) {
        super(CIN, nom, prenom, date_naissance, adresse, telephone, email, sexe);
        this.age = age;
        this.assurence = assurence ;
        this.risque = risque ;
        this.facture_total = facture_total;
        this.date_ajout =  LocalDateTime.now();
    }
    public Patient(String CIN, String nom, String prenom, LocalDate date_naissance, String adresse, String telephone, String email, String sexe, int age,Assurence assurence,String risque) {
        super(CIN, nom, prenom, date_naissance, adresse, telephone, email, sexe);
        this.age = age;
        this.assurence = assurence ;
        this.risque = risque ;

        this.date_ajout =  LocalDateTime.now();
    }


    // Getters and Setters
    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public Double getFacture_total() {
        return facture_total;
    }
    public void setFacture_total(Double facture_total) {
        this.facture_total = facture_total;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDateTime getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(LocalDateTime date_ajout) {
        this.date_ajout = date_ajout;
    }

    public Assurence getAssurence() {
        return assurence;
    }

    public void setAssurence(Assurence assurence) {
        this.assurence = assurence;
    }

    public String getRisque() {
        return risque;
    }

    public void setRisque(String risque) {
        this.risque = risque;
    }




    public String toString() {
        return "Patient{" +
                "patient_id=" + patient_id +
                ", facture_total=" + facture_total +
                ", age=" + age +
                ", assurence=" + assurence +
                ", risque='" + risque + '\'' +
                ", date_ajout=" + date_ajout +
                ", CIN='" + CIN + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", date_naissance=" + date_naissance +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", sexe='" + sexe + '\'' +
                '}';
    }



}
