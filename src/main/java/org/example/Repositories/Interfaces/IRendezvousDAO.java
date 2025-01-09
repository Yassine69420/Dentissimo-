package org.example.Repositories.Interfaces;


import org.example.MODELS.Rendezvous;
import org.example.Exceptions.DAOException;

import java.util.List;

public interface IRendezvousDAO extends ICRUD<Rendezvous> {
    void add(Rendezvous rendezvous) throws DAOException;

    List<Rendezvous> getAll() throws DAOException;

    Rendezvous getById(int id_rdv) throws DAOException;

    void update(Rendezvous rendezvous) throws DAOException;

    void delete(int id_rdv) throws DAOException;
}

