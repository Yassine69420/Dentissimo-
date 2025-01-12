package org.example.Repositories.Interfaces;

import org.example.Exceptions.DAOException;
import org.example.MODELS.Acte;

import java.util.List;

public interface IActeDAO extends ICRUD<Acte> {

    void add(Acte acte) throws DAOException;

    // Retrieve all entities
    List<Acte> getAll() throws DAOException;

    // Retrieve an entity by its ID
    Acte getById(int id) throws DAOException;

    // Update an entity
    void update(Acte acte) throws DAOException;

    // Delete an entity by its ID
    void delete(int id) throws DAOException;
}