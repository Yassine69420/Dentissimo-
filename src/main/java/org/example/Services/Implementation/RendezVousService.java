package org.example.Services.Implementation;

import org.example.Exceptions.DAOException;
import org.example.Exceptions.ServiceException;
import org.example.MODELS.Rendezvous;
import org.example.Repositories.Interfaces.IRendezvousDAO;
import org.example.Services.Interfaces.IRendezVousService;

import java.util.List;

public class RendezVousService implements IRendezVousService {
    private final IRendezvousDAO rendezvousDAO;

    public RendezVousService(IRendezvousDAO rendezvousDAO) {
        this.rendezvousDAO = rendezvousDAO;
    }

    @Override
    public void addRendezvous(Rendezvous rendezvous) throws ServiceException {
        if (rendezvous.getDate() == null) {
            throw new ServiceException("Date cannot be null");
        }
        try {
            rendezvousDAO.add(rendezvous);
        } catch (DAOException e) {
            throw new ServiceException("Failed to add rendezvous", e);
        }
    }

    @Override
    public List<Rendezvous> getAllRendezvous() throws ServiceException {
        try {
            return rendezvousDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException("Failed to retrieve rendezvous list", e);
        }
    }

    @Override
    public Rendezvous getRendezvousById(int id) throws ServiceException {
        try {
            return rendezvousDAO.getById(id);
        } catch (DAOException e) {
            throw new ServiceException("Failed to retrieve rendezvous by ID", e);
        }
    }

    @Override
    public void updateRendezvous(Rendezvous rendezvous) throws ServiceException {
        if (rendezvous.getId_rdv() <= 0) {
            throw new IllegalArgumentException("Invalid Rendez-vous ID");
        }
        try {
            rendezvousDAO.update(rendezvous);
        } catch (DAOException e) {
            throw new ServiceException("Failed to update rendezvous", e);
        }
    }

    @Override
    public void deleteRendezvous(int id) throws ServiceException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid Rendez-vous ID");
        }
        try {
            rendezvousDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Failed to delete rendezvous", e);
        }
    }
}
