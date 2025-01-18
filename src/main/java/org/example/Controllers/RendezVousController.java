package org.example.Controllers;

import org.example.MODELS.Rendezvous;
import org.example.Services.Implementation.RendezVousService;
import org.example.Exceptions.ControllerException;
import org.example.Exceptions.ServiceException;
import org.example.Services.Interfaces.IRendezVousService;

import java.time.LocalDateTime;
import java.util.List;

public class RendezVousController {

    private final IRendezVousService rendezVousService;

    public RendezVousController(RendezVousService rendezVousService) {
        this.rendezVousService = rendezVousService;
    }

    public Rendezvous addRendezvous(LocalDateTime date, int idPatient, int idMedecin) throws ControllerException {
        try {
            Rendezvous rendezvous = new Rendezvous(0, date, idPatient, idMedecin);
            rendezVousService.addRendezvous(rendezvous);
            return rendezvous;
        } catch (ServiceException e) {
            throw new ControllerException("Failed to add rendezvous", e);
        }
    }

    public List<Rendezvous> getAllRendezvous() throws ControllerException {
        try {
            return rendezVousService.getAllRendezvous();
        } catch (ServiceException e) {
            throw new ControllerException("Failed to retrieve all rendezvous", e);
        }
    }

    public Rendezvous getRendezvousById(int id) throws ControllerException {
        try {
            return rendezVousService.getRendezvousById(id);
        } catch (ServiceException e) {
            throw new ControllerException("Failed to retrieve rendezvous by ID", e);
        }
    }

    public void updateRendezvous(int id, LocalDateTime date, int idPatient, int idMedecin) throws ControllerException {
        try {
            Rendezvous rendezvous = new Rendezvous(id, date, idPatient, idMedecin);
            rendezVousService.updateRendezvous(rendezvous);
        } catch (ServiceException e) {
            throw new ControllerException("Failed to update rendezvous", e);
        }
    }

    public void deleteRendezvous(int id) throws ControllerException {
        try {
            rendezVousService.deleteRendezvous(id);
        } catch (ServiceException e) {
            throw new ControllerException("Failed to delete rendezvous", e);
        }
    }
}
