package org.example.MODELS;



import java.time.LocalDate;
import java.time.LocalDateTime;

public class Rendezvous {
    
    private int id_rdv;
    private LocalDateTime date ;

    private int id_patient;
    private int id_medecin;

    private Patient patient;
    private Medecin medecin;

    public Rendezvous(LocalDateTime date, Patient patient, Medecin medecin) {
        this.date = date;
        this.patient = patient;
        this.medecin = medecin;
    }
    public Rendezvous(int id_rdv, LocalDateTime date, int id_patient, int id_medecin) {
        this.id_rdv = id_rdv;
        this.date = date;
        this.id_patient = id_patient;
        this.id_medecin = id_medecin;
    }
    public Rendezvous(LocalDateTime date, int id_patient, int id_medecin) {
        this.date = date;
        this.id_patient = id_patient;
        this.id_medecin = id_medecin;
    }
    public Rendezvous(int id_rdv, LocalDateTime date, Patient patient, Medecin medecin) {
        this.id_rdv = id_rdv;
        this.date = date;
        this.patient = patient;
        this.medecin = medecin;
    }
    // Getters and Setters
    public int getId_rdv() {
        return id_rdv;
    }
    public void setId_rdv(int id_rdv) {
        this.id_rdv = id_rdv;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public Medecin getMedecin() {
        return medecin;
    }
    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }
    public int getId_patient() {
        return id_patient;
    }
    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }
    public int getId_medecin() {
        return id_medecin;
    }
    public void setId_medecin(int id_medecin) {
        this.id_medecin = id_medecin;
    }

    @Override
    public String toString() {
        return "Rendezvous{" +
                "date=" + date +
                ", id_patient=" + id_patient +
                ", id_medecin=" + id_medecin +
                '}';
    }
}