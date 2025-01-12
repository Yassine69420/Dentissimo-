package org.example.Services.Interfaces;

import org.example.MODELS.Patient;
import org.example.Exceptions.DAOException;
import org.example.Repositories.Interfaces.ICRUD;

import java.util.List;

public interface IPatientService extends ICRUD<Patient> {
    void                add(Patient patient)         throws DAOException;
    List<Patient>       getAll()                    throws DAOException;
    Patient             getById(int id)              throws DAOException;
    List<Patient>       searchPatientsByName(String name)   throws DAOException;
    List<Patient>       searchPatientsByAge(int age)        throws DAOException;
    List<Patient>       searchPatientsBySexe(String sexe)   throws DAOException;
    void                update(Patient patient)      throws DAOException;
    void                delete(int id)               throws DAOException;
}


