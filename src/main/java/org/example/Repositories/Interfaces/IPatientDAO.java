package org.example.Repositories.Interfaces;

import org.example.MODELS.Patient;
import org.example.Repositories.Exceptions.DAOException;

import java.util.List;

public interface IPatientDAO extends ICRUD<Patient> {
    void add(Patient patient) throws DAOException;
    List<Patient> getAll() throws DAOException;
    List<Patient> getAllPatientsbyname(String search) throws DAOException;
    List<Patient> getAllPatientsbyage(int age) throws DAOException;
    List<Patient> getAllPatientsbysexe(String sexe) throws DAOException;
    void update(Patient patient) throws DAOException;
    void delete(int patient_id) throws DAOException;
    Patient getById(int patient_id) throws DAOException;
}






