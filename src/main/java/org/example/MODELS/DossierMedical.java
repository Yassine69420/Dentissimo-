package org.example.MODELS;


import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

public class DossierMedical {
    
    private int id_dossier;
    private Patient id_patient;
    private List<Visite> visites;
    private LocalDateTime date_creation;
    private String notes;
    private ArrayList<String> medications;
    private ArrayList<String> allergies;
    private LocalDate created_at = LocalDate.now();


    
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

    public LocalDateTime getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(LocalDateTime date_creation) {
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
