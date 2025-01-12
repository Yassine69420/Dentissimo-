package org.example.Repositories.Implementation;

import org.example.Exceptions.AuthException;
import org.example.Exceptions.DAOException;
import org.example.MODELS.Medecin;

import org.example.Repositories.Interfaces.IMedecinDAO;

import java.io.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MedecinDAO implements IMedecinDAO {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final String PATH = "src/main/resources/Medecins.csv";
    @Override
    public List<Medecin> getAll() throws DAOException {
        List<Medecin> medecins = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                medecins.add(parseMedecinData(line));
            }
        } catch (IOException e) {
            throw new DAOException("Failed to retrieve Medecins", e);
        }
        return medecins;
    }
    @Override
    public Medecin getByEmailandPass(String email, String password) throws AuthException {
        try {
            List<Medecin> medecins = getAll();
            for (Medecin medecin : medecins) {
                if (medecin.getEmail().equalsIgnoreCase(email) && medecin.getPassword().equals(password)) {
                    return medecin;
                }
            }
        } catch (DAOException e) {
            throw new AuthException("Failed to authenticate Medecin", e);
        }
        throw new AuthException("Invalid email or password.");
    }
    @Override
    public Medecin getById(int id) throws DAOException {
        List<Medecin> medecins = getAll();
        for (Medecin medecin : medecins) {
            if (medecin.getMedecin_id() == id) {
                return medecin;
            }
        }
        throw new DAOException("Medecin with ID " + id + " not found.");
    }
    @Override
    public void update(Medecin medecin) throws DAOException {
        String tempFile = "src/main/resources/temp_Medecin.csv";
        File inputFile = new File(PATH);
        File tempFileObj = new File(tempFile);

        String line;
        boolean updated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileObj))) {

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3 && Integer.parseInt(data[1]) == medecin.getMedecin_id()) {
                    writer.write(formatMedecinData(medecin));
                    writer.newLine();
                    updated = true;
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            if (!updated) {
                throw new DAOException("Medecin with ID " + medecin.getMedecin_id() + " not found.");
            }

        } catch (IOException e) {
            throw new DAOException("Failed to update Medecin", e);
        }

        if (!inputFile.delete()) {
            throw new DAOException("Failed to delete the original file.");
        }
        if (!tempFileObj.renameTo(inputFile)) {
            throw new DAOException("Failed to rename the temporary file to the original file name.");
        }
    }

    private String formatMedecinData(Medecin medecin) {

        return String.format("%d=%s=%s=%s=%s=%s=%s=%s=%s",
                medecin.getMedecin_id(),
                medecin.getCIN(),
                medecin.getNom(),
                medecin.getPrenom(),
                dtf.format(medecin.getDate_naissance()),
                medecin.getAdresse(),
                medecin.getTelephone(),
                medecin.getEmail(),
                medecin.getPassword());
    }
    private Medecin parseMedecinData(String line) throws DAOException {
        try {
            String[] data = line.split("=");
            return new Medecin(
                    Integer.parseInt(data[0]),               // ID
                    data[1],                                 // CIN
                    data[2],                                 // Nom
                    data[3],                                 // Prenom
                    LocalDate.parse(data[4], dtf),           // Date de naissance
                    data[5],                                 // Adresse
                    data[6],                                 // Telephone
                    data[7],                                 // Email
                    data[8],                                 // Sexe
                    data[9]                                  // Password
            );
        } catch (Exception e) {
            throw new DAOException("Failed to parse Medecin data: " + line, e);
        }
    }



}
