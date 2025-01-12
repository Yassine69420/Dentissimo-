package org.example.Repositories.Implementation;

import org.example.Exceptions.DAOException;
import org.example.MODELS.Acte;
import org.example.Repositories.Interfaces.IActeDAO;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ActeDAO implements IActeDAO {
    private static final String PATH = "src/main/resources/actes.csv";
    @Override
    public void add(Acte acte) throws DAOException {
        if (acte.getIdActe() == 0) {
            int newId = generateId();
            acte.setIdActe(newId);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH, true))) {
            writer.write(formatActe(acte));
            writer.newLine();
        } catch (IOException e) {
            throw new DAOException("Failed to add acte", e);
        }
        System.out.println("Acte added successfully");
    }

    @Override
    public List<Acte> getAll() throws DAOException {
        List<Acte> actes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                actes.add(parseActe(line));
            }
        } catch (IOException e) {
            throw new DAOException("Failed to retrieve actes", e);
        }
        return actes;
    }

    @Override
    public Acte getById(int id) throws DAOException {
        return getAll().stream()
                .filter(acte -> acte.getIdActe() == id)
                .findFirst()
                .orElseThrow(() -> new DAOException("Acte with ID " + id + " not found"));
    }
    @Override
    public void update(Acte acte) throws DAOException {
        File inputFile = new File(PATH);
        File tempFileObj = new File("src/main/resources/temp_Actes.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileObj))) {

            String line;
            boolean updated = false;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("=");
                if (Integer.parseInt(data[0]) == acte.getIdActe()) {
                    writer.write(formatActe(acte));
                    writer.newLine();
                    updated = true;
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            if (!updated) {
                throw new DAOException("Acte with ID " + acte.getIdActe() + " not found.");
            }

        } catch (IOException e) {
            throw new DAOException("Failed to update acte", e);
        }

        if (!inputFile.delete() || !tempFileObj.renameTo(inputFile)) {
            throw new DAOException("Failed to replace original file.");
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        File inputFile = new File(PATH);
        File tempFileObj = new File("src/main/resources/temp_Actes.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileObj))) {

            String line;
            boolean deleted = false;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("=");
                if (Integer.parseInt(data[0]) == id) {
                    deleted = true;
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            if (!deleted) {
                throw new DAOException("Acte with ID " + id + " not found.");
            }

        } catch (IOException e) {
            throw new DAOException("Failed to delete acte", e);
        }

        if (!inputFile.delete() || !tempFileObj.renameTo(inputFile)) {
            throw new DAOException("Failed to replace original file.");
        }
    }
    private Acte parseActe(String line) {
        String[] parts = line.split("=");
        return new Acte(
                Integer.parseInt(parts[0]), // idActe
                parts[1],                   // typeActe
                parts[2],                   // description
                Float.parseFloat(parts[3])  // cost
        );
    }
    private String formatActe(Acte acte) {
        return acte.getIdActe() + "=" +
                acte.getTypeActe() + "=" +
                acte.getDescription() + "=" +
                acte.getCost();
    }
    private int generateId() throws DAOException {
        int lastId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("=");
                lastId = Integer.parseInt(data[0]);
            }
        } catch (FileNotFoundException e) {
            return 1;
        } catch (IOException e) {
            throw new DAOException("Failed to generate acte ID", e);
        }
        return lastId + 1;
    }
}
