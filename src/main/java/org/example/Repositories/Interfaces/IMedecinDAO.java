package org.example.Repositories.Interfaces;

import org.example.Exceptions.AuthException;
import org.example.Exceptions.DAOException;
import org.example.MODELS.Medecin;

import java.util.List;

public interface IMedecinDAO extends IAUTH<Medecin> {

    List<Medecin> getAll() throws DAOException;

    Medecin getById(int id) throws DAOException;
    void update(Medecin medecin) throws DAOException;

    Medecin getByEmailandPass(String email, String password) throws AuthException;


}
