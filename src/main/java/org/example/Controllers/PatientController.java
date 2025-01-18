package org.example.Controllers;

import org.example.Exceptions.ControllerException;
import org.example.Exceptions.DAOException;
import org.example.MODELS.Patient;
import org.example.Services.Interfaces.IPatientService;

import java.util.List;

public class PatientController {
    private final IPatientService patientService;

    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }
    // Add a new patient
    public void addPatient(Patient patient) throws ControllerException {
        try {

            patientService.add(patient);
            System.out.println("Patient added successfully.");
        } catch (DAOException e) {
            throw new ControllerException("Error while adding patient: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new ControllerException("Unexpected error occurred: " + e.getMessage(), e);
        }

    }
    // Get all patients
    public List<Patient> getAllPatients() throws ControllerException {
        try {
            return patientService.getAll();
        } catch (DAOException e) {
            throw new ControllerException("Error while fetching patients: " + e.getMessage(), e);
        }
    }

    // Get a patient by ID
    public Patient getPatientById(int id) throws ControllerException {
        try {
            return patientService.getById(id);
        } catch (DAOException e) {
            throw new ControllerException("Error while fetching patient with ID " + id + ": " + e.getMessage(), e);
        }
    }

    // Search patients by name
    public List<Patient> searchPatientsByName(String name) throws ControllerException {
        try {
            return patientService.searchPatientsByName(name);
        } catch (DAOException e) {
            throw new ControllerException("Error while searching patients by name: " + e.getMessage(), e);
        }
    }

    // Search patients by age
    public List<Patient> searchPatientsByAge(int age) throws ControllerException {
        try {
            return patientService.searchPatientsByAge(age);
        } catch (DAOException e) {
            throw new ControllerException("Error while searching patients by age: " + e.getMessage(), e);
        }
    }

    // Search patients by sexe
    public List<Patient> searchPatientsBySexe(String sexe) throws ControllerException {
        try {
            return patientService.searchPatientsBySexe(sexe);
        } catch (DAOException e) {
            throw new ControllerException("Error while searching patients by sexe: " + e.getMessage(), e);
        }
    }

    // Update a patient
    public void updatePatient(Patient patient) throws ControllerException {
        try {
            patientService.update(patient);
            System.out.println("Patient updated successfully.");
        } catch (DAOException e) {
            throw new ControllerException("Error while updating patient: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new ControllerException("Unexpected error occurred: " + e.getMessage(), e);
        }
    }

    // Delete a patient by ID
    public void deletePatient(int id) throws ControllerException {
        try {
            patientService.delete(id);
            System.out.println("Patient deleted successfully.");
        } catch (DAOException e) {
            throw new ControllerException("Error while deleting patient with ID " + id + ": " + e.getMessage(), e);
        }
    }




}