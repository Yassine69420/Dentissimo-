package org.example.MODELS;


import org.example.Exceptions.DAOException;
import org.example.Repositories.Implementation.PatientDAO;

import java.time.LocalDate;


public class Secretaire extends User {
    private int id_sec;

    public Secretaire(String CIN, String nom, String prenom, LocalDate date_naissance, String adresse, String telephone, String email, String sexe, int id_sec) {
        super(CIN, nom, prenom, date_naissance, adresse, telephone, email, sexe);
        this.id_sec = id_sec;
    }

    // Getters and Setters
    public int getId_sec() {
        return id_sec;
    }

    public void setId_sec(int id_sec) {
        this.id_sec = id_sec;
    }

    public void addPatient(Patient patient) throws DAOException {
      PatientDAO patientDAO = new PatientDAO();
      patientDAO.add(patient);
    
    }

}
