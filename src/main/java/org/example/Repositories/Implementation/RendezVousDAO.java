package org.example.Repositories.Implementation;

import org.example.MODELS.Rendezvous;
import org.example.Exceptions.DAOException;
import org.example.Repositories.Interfaces.IRendezvousDAO;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RendezVousDAO implements IRendezvousDAO {
    public static String PATH = "src/main/resources/Rendezvous.csv";
   public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    @Override
    public void add(Rendezvous rendezvous) throws DAOException {
        // Generate new ID for the rendezvous
        int newId = generateId();
        rendezvous.setId_rdv(newId);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH, true))) {
            writer.write(formatData(rendezvous));
            writer.newLine();
        } catch (IOException e) {
            throw new DAOException("Failed to add rendezvous", e);
        }
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
    @Override
    public Rendezvous getById(int id_rdv) throws DAOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Rendezvous rendezvous = parseRendezvousData(line);
                if (rendezvous.getId_rdv() == id_rdv) {
                    return rendezvous;
                }
            }
        } catch (IOException e) {
            throw new DAOException("Failed to retrieve rendezvous by ID", e);
        }
        throw new DAOException("Rendezvous with ID " + id_rdv + " not found");
    }
    @Override
    public void update(Rendezvous rendezvous) throws DAOException {
        List<Rendezvous> rendezvousList = getAll();
        boolean updated = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH))) {
            for (Rendezvous r : rendezvousList) {
                if (r.getId_rdv() == rendezvous.getId_rdv()) {
                    writer.write(formatData(rendezvous));
                    updated = true;
                } else {
                    writer.write(formatData(r));
                }
                writer.newLine();
            }
        } catch (IOException e) {
            throw new DAOException("Failed to update rendezvous", e);
        }

        if (!updated) {
            throw new DAOException("Rendezvous with ID " + rendezvous.getId_rdv() + " not found");
        }
    }
    @Override
    public void delete(int id_rdv) throws DAOException {
        List<Rendezvous> rendezvousList = getAll();
        boolean deleted = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH))) {
            for (Rendezvous r : rendezvousList) {
                if (r.getId_rdv() == id_rdv) {
                    deleted = true;
                    continue;
                }
                writer.write(formatData(r));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new DAOException("Failed to delete rendezvous", e);
        }

        if (!deleted) {
            throw new DAOException("Rendezvous with ID " + id_rdv + " not found");
        }
    }
    //-------------------------------------Helper methods-------------------------------------------------
    public static int generateId() throws DAOException {
        int lastId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");
                if (data.length > 0 && !data[0].trim().isEmpty()) {
                    try {
                        lastId = Integer.parseInt(data[0].trim());
                    } catch (NumberFormatException e) {
                        continue;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            return 1; // Start ID at 1 if the file doesn't exist
        } catch (IOException e) {
            throw new DAOException("Failed to generate Rendez-vous ID", e);
        }
        return lastId + 1;
    }
    private Rendezvous parseRendezvousData(String line) throws DAOException {
        try {
            String[] data = line.split("=");

            return new Rendezvous(
                    Integer.parseInt(data[0]),
                    LocalDateTime.parse(data[1],dtf ),
                    Integer.parseInt(data[2]),
                    Integer.parseInt(data[3])
            );
        } catch (Exception e) {
            throw new DAOException("Failed to parse rendezvous data: " + line, e);
        }
    }
    public static String formatData(Rendezvous rendezvous) {

        return rendezvous.getId_rdv() +
                "=" + dtf.format(rendezvous.getDate()) +
                "=" + rendezvous.getId_patient() +
                "=" + rendezvous.getId_medecin();
    }

}
