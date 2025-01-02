package org.example.Repositories.Interfaces;

import org.example.MODELS.Patient;

import java.util.List;

public interface PatientDAO {

    public void addPatient(Patient patient);

    public List<Patient> getAllPatients();
    public List<Patient> getAllPatientsbyname(String search);
    public List<Patient> getAllPatientsbyage(int age);
    public List<Patient> getAllPatientsbysexe(String sexe);

    public void updatePatient(Patient patient);

    public void deletePatient(String patient_id);

    public void getPatient(String patient_id);

    
} 
    

