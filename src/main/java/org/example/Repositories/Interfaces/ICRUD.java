package org.example.Repositories.Interfaces;

import org.example.Exceptions.DAOException;

import java.util.List;


public interface ICRUD<T> {

    // Add an entity
    void add(T entity) throws DAOException;

    // Retrieve all entities
    List<T> getAll() throws DAOException;

    // Retrieve an entity by its ID
    T getById(int id) throws DAOException;

    // Update an entity
    void update(T entity) throws DAOException;

    // Delete an entity by its ID
    void delete(int id) throws DAOException;
}


