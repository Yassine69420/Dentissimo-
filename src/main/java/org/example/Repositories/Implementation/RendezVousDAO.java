package org.example.Repositories.Implementation;

import org.example.MODELS.Patient;
import org.example.MODELS.Rendezvous;
import org.example.Repositories.Exceptions.DAOException;
import org.example.Repositories.Interfaces.IRendezvousDAO;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RendezVousDAO implements IRendezvousDAO {
    public static String PATH = "src/main/resources/Rendezvous.csv";

    @Override
    public void add(Rendezvous rendezvous) throws DAOException {
        //add rendezvous to file database
        int newId = generateId();
        rendezvous.setId_rdv(newId);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH, true))) {
            writer.write(formatData(rendezvous));
            writer.newLine();
        } catch (IOException e) {
            throw new DAOException("Failed to add rendezvous", e);
        }
    }

    public static String formatData(Rendezvous rendezvous) {
        return rendezvous.getId_rdv() +
                "," + rendezvous.getPatient_id() +
                "," + rendezvous.getMedecin_id() +
                "," + rendezvous.getDate() +
                "," + rendezvous.getHeure();
    }


    @Override
    public List<Rendezvous> getAll() throws DAOException {
        List<Rendezvous> rendezvousList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                rendezvousList.add(parseRendezvousData(line));
            }
        } catch (IOException e) {
            throw new DAOException("Failed to retrieve rendezvous data", e);
        }
        return rendezvousList;
    }

    private Rendezvous parseRendezvousData(String line) throws DAOException {
        try {
            String[] data = line.split(",");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            return new Rendezvous(
                    sdf.parse(data[1]),               // Date
                    java.sql.Time.valueOf(data[2]),   // Time
                    Integer.parseInt(data[3]),        // Patient ID
                    Integer.parseInt(data[4])         // Medecin ID
            );

        } catch (Exception e) {
            throw new DAOException("Failed to parse rendezvous data: " + line, e);
        }
    }

    @Override
    public Rendezvous getById(int id_rdv) throws DAOException {
        return null;
    }

    @Override
    public void update(Rendezvous rendezvous) throws DAOException {

    }

    @Override
    public void delete(int id_rdv) throws DAOException {

    }
    public static int generateId() throws DAOException {
        int lastId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 2) {
                    lastId = Integer.parseInt(data[0]);
                }
            }
        } catch (FileNotFoundException e) {

            return 1;
        } catch (IOException e) {
            throw new DAOException("Failed to generate Rendez-vous ID", e);
        }
        return lastId + 1;
    }
}


