package org.example.Repositories.Interfaces;

import org.example.Exceptions.AuthException;
import org.example.Exceptions.DAOException;

import org.example.MODELS.Secretaire;

public interface ISecretaireDAO extends IAUTH<Secretaire> {
    void update(Secretaire secretaire) throws DAOException;
    Secretaire getByEmailandPass(String email, String password) throws AuthException;
}

