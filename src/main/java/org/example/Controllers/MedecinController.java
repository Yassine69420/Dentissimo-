package org.example.Controllers;

import org.example.Exceptions.AuthException;
import org.example.Exceptions.DAOException;
import org.example.MODELS.Medecin;
import org.example.Services.Implementation.MedecinService;

import java.util.List;

public class MedecinController {

    private final MedecinService medecinService;

    public MedecinController(MedecinService medecinService) {
        this.medecinService = medecinService;
    }

   
    public List<Medecin> getAllMedecins() {
        try {
            return medecinService.getAll();
        } catch (DAOException e) {
            System.err.println("Error fetching all Medecins: " + e.getMessage());
            return null;
        }
    }


    public Medecin getMedecinById(int id) {
        try {
            return medecinService.getById(id);
        } catch (DAOException e) {
            System.err.println("Error fetching Medecin by ID: " + e.getMessage());
            return null;
        }
    }


    public void updateMedecin(Medecin medecin) {
        try {
            medecinService.update(medecin);
            System.out.println("Medecin updated successfully.");
        } catch (DAOException e) {
            System.err.println("Error updating Medecin: " + e.getMessage());
        }
    }


    public Medecin authenticate(String email, String password) {
        try {
            return medecinService.getByEmailandPass(email, password);
        } catch (AuthException e) {
            System.err.println("Authentication failed: " + e.getMessage());
            return null;
        }
    }
}
