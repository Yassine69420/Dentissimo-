package org.example.Repositories.Interfaces;

import org.example.MODELS.Patient;
import org.example.Exceptions.DAOException;

import java.util.List;

public interface IPatientDAO extends ICRUD<Patient> {
    void add(Patient patient) throws DAOException;
    List<Patient> getAll() throws DAOException;
    void update(Patient patient) throws DAOException;
    void delete(int patient_id) throws DAOException;
    Patient getById(int patient_id) throws DAOException;
}






