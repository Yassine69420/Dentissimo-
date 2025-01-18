package org.example.Services.Interfaces;


import org.example.Exceptions.ServiceException;
import org.example.MODELS.Rendezvous;

import java.util.List;

public interface IRendezVousService {

    void addRendezvous(Rendezvous rendezvous) throws ServiceException;
    List<Rendezvous> getAllRendezvous() throws ServiceException;
    Rendezvous getRendezvousById(int id) throws ServiceException;
    void updateRendezvous(Rendezvous rendezvous) throws ServiceException;
    void deleteRendezvous(int id) throws ServiceException;
}
