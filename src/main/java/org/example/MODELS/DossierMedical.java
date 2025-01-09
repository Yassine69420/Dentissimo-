package org.example.MODELS;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DossierMedical {
    
    private int id_dossier;
    private Patient id_patient;
    // private ArrayList<Consultation> consultations;
    // private ArrayList<InterventionMedicale> interventions;
    // private ArrayList<Facture> factures;
    private Date date_creation;
    private String notes;
    private ArrayList<String> medications;
    
    // Getters and Setters
    public int getId_dossier() {
        return id_dossier;
    }

    public void setId_dossier(int id_dossier) {
        this.id_dossier = id_dossier;
    }

    public Patient getId_patient() {
        return id_patient;
    }

    public void setId_patient(Patient id_patient) {
        this.id_patient = id_patient;
    }

  
    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<String> getMedications() {
        return medications;
    }

}
