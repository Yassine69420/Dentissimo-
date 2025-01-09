package org.example.Services.Implementation;


import org.example.Exceptions.PatientValidationException;
import org.example.MODELS.Patient;
import org.example.Exceptions.DAOException;
import org.example.Repositories.Interfaces.IPatientDAO;
import org.example.Services.Interfaces.IPatientService;

import java.util.List;

public class PatientService implements IPatientService {
    private final IPatientDAO patientDAO;

    public PatientService(IPatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @Override
    public void add(Patient patient) throws DAOException {
        validatePatient(patient);
        patientDAO.add(patient);
    }

    @Override
    public List<Patient> getAll() throws DAOException {
        return patientDAO.getAll();
    }

    @Override
    public Patient getById(int id) throws DAOException {
        return patientDAO.getById(id);
    }
    @Override
    public List<Patient> searchPatientsByName(String name) throws DAOException {
        return patientDAO.getAllPatientsbyname(name);
    }
    @Override
    public List<Patient> searchPatientsByAge(int age) throws DAOException {
        return patientDAO.getAllPatientsbyage(age);
    }
    @Override
    public List<Patient> searchPatientsBySexe(String sexe) throws DAOException {
        return patientDAO.getAllPatientsbysexe(sexe);
    }
    @Override
    public void update(Patient patient) throws DAOException {
        validatePatient(patient);
        patientDAO.update(patient);
    }
    @Override
    public void delete(int id) throws DAOException {
        patientDAO.delete(id);
    }
    // Validate Patient details
    private void validatePatient(Patient patient) {
        if (patient.getNom() == null || patient.getNom().isEmpty()) {
            throw new PatientValidationException("Patient name cannot be null or empty");
        }
        if (patient.getPrenom() == null || patient.getPrenom().isEmpty()) {
            throw new PatientValidationException("Patient surname cannot be null or empty");
        }
        if (patient.getAge() <= 0) {
            throw new PatientValidationException("Patient age must be greater than 0");
        }
        if (patient.getSexe() == null || patient.getSexe().isEmpty()) {
            throw new PatientValidationException("Patient sexe cannot be null or empty");
        }
        if (patient.getCIN() == null || patient.getCIN().isEmpty()) {
            throw new PatientValidationException("Patient CIN cannot be null or empty");
        }
        if (patient.getTelephone() == null || patient.getTelephone().isEmpty()) {
            throw new PatientValidationException("Patient telephone cannot be null or empty");
        }
    }
}
