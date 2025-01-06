package org.example.Repositories.Implementation;

import org.example.MODELS.Rendezvous;
import org.example.Repositories.Exceptions.DAOException;
import org.example.Repositories.Interfaces.IRendezvousDAO;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RendezVousDAO implements IRendezvousDAO {
    public static String PATH = "src/main/resources/Rendezvous.csv";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

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

    public static String formatData(Rendezvous rendezvous) {
        // Format data to ensure consistency
        return rendezvous.getId_rdv() +
                "," + rendezvous.getPatient_id() +
                "," + rendezvous.getMedecin_id() +
                "," + DATE_FORMAT.format(rendezvous.getDate()) +
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
            // Parse date and time in the same format as they were saved
            return new Rendezvous(
                    Integer.parseInt(data[0]),          // ID
                    DATE_FORMAT.parse(data[3]),         // Date
                    java.sql.Time.valueOf(data[4]),     // Time
                    Integer.parseInt(data[1]),          // Patient ID
                    Integer.parseInt(data[2])           // Medecin ID
            );
        } catch (Exception e) {
            throw new DAOException("Failed to parse rendezvous data: " + line, e);
        }
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

    public static int generateId() throws DAOException {
        int lastId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                // Ensure the line has enough columns and the first column is not empty
                if (data.length > 0 && !data[0].trim().isEmpty()) {
                    try {
                        // Parse the first column as an integer
                        lastId = Integer.parseInt(data[0].trim());
                    } catch (NumberFormatException e) {
                        // Skip lines with invalid ID data
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

}
