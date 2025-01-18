package org.example.Services.Implementation;

import org.example.Exceptions.AuthException;
import org.example.Exceptions.DAOException;
import org.example.MODELS.Medecin;
import org.example.Repositories.Implementation.MedecinDAO;

import java.util.List;

public class MedecinService {

    private final MedecinDAO medecinDAO ;

    public MedecinService(MedecinDAO medecinDAO) {
        this.medecinDAO = medecinDAO;
    }

    public List<Medecin> getAll() throws DAOException {
       return medecinDAO.getAll();
    }

    public Medecin getById(int id) throws DAOException{
        return medecinDAO.getById(id);};

    public void update(Medecin medecin) throws DAOException{
        medecinDAO.update(medecin);
    };

    public Medecin getByEmailandPass(String email, String password) throws AuthException {
        return medecinDAO.getByEmailandPass(email,password);
    };






}
