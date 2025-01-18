package org.example.Repositories.Implementation;

import org.example.MODELS.Patient;
import org.example.Exceptions.DAOException;
import org.example.MODELS.enums.Assurence;
import org.example.Repositories.Interfaces.IPatientDAO;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO implements IPatientDAO {
    private static final String PATH = "src/main/resources/Patients.csv";
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    @Override
    public void add(Patient patient) throws DAOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH, true))) {
            patient.setFacture_total(0.00);
            patient.setPatient_id(generateId());
            writer.write(toCSV(patient));
            writer.newLine();
        } catch (IOException e) {
            throw new DAOException("Failed to add patient", e);
        }
        System.out.println("Patient added successfully");
    }

    @Override
    public List<Patient> getAll() throws DAOException {
        List<Patient> patients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                patients.add(parsePatientData(line));
            }
        } catch (IOException e) {
            throw new DAOException("Failed to retrieve patients", e);
        }

        return patients;
    }

    @Override
    public void update(Patient patient) throws DAOException {
        File inputFile = new File(PATH);
        File tempFileObj = new File("src/main/resources/temp_Patient.csv");
        String line;
        boolean updated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileObj))) {

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("=");
                if (Integer.parseInt(data[0]) == patient.getPatient_id()) {
                    writer.write(toCSV(patient));
                    writer.newLine();
                    updated = true;
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            if (!updated) {
                throw new DAOException("Patient with ID " + patient.getPatient_id() + " not found.");
            }

        } catch (IOException e) {
            throw new DAOException("Failed to update patient", e);
        }

        if (!inputFile.delete()) {
            throw new DAOException("Failed to delete the original file.");
        }
        if (!tempFileObj.renameTo(inputFile)) {
            throw new DAOException("Failed to rename the temporary file to the original file name.");
        }
    }

    @Override
    public void delete(int patient_id) throws DAOException {
        String tempFile = "src/main/resources/temp_Patient.csv";
        File inputFile = new File(PATH);
        File tempFileObj = new File(tempFile);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileObj))) {

            String line;
            boolean deleted = false;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("=");
                if (Integer.parseInt(data[0]) == patient_id) {
                    deleted = true;
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            if (!deleted) {
                throw new DAOException("Patient with ID " + patient_id + " not found.");
            } else {
                System.out.println("Patient with ID " + patient_id + " deleted successfully.");
            }

        } catch (IOException e) {
            throw new DAOException("Failed to delete patient", e);
        }

        if (!inputFile.delete()) {
            throw new DAOException("Failed to delete the original file.");
        }
        if (!tempFileObj.renameTo(inputFile)) {
            throw new DAOException("Failed to rename the temporary file to the original file name.");
        }
    }

    @Override
    public Patient getById(int patient_id) throws DAOException {
        List<Patient> patients = getAll();
        for (Patient patient : patients) {
            if (patient.getPatient_id() == patient_id) {
                return patient;
            }
        }
        throw new DAOException("Patient with ID " + patient_id + " not found.");
    }

    //-------------------------------------Helper methods-------------------------------------------------

    protected Patient parsePatientData(String line) throws DAOException {
        try {
            String[] data = line.split("=");
            return new Patient(
                    Integer.parseInt(data[0]),        // Patient ID
                    data[1],                         // CIN
                    data[2],                         // Nom
                    data[3],                         // Prenom
                    LocalDate.parse(data[4], dtf),   // Date de naissance
                    data[5],                         // Adresse
                    data[6],                         // Telephone
                    data[7],                         // Email
                    data[8],                         // Sexe
                    Integer.parseInt(data[9]),       // Age
                    Assurence.valueOf(data[10]),     // Assurence
                    data[11],                        // Risque
                    data[12].equals("nu") ? null : Double.valueOf(data[12]), // Facture total (nullable)
                    LocalDateTime.parse(data[13], DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) // Date d'ajout
            );
        } catch (Exception e) {
            throw new DAOException("Failed to parse patient data: " + line, e);
        }
    }

    public String toCSV(Patient patient) {
        return String.format("%d=%s=%s=%s=%s=%s=%s=%s=%s=%d=%s=%s=%s=%s",
                patient.getPatient_id(),                   // Patient ID
                patient.getCIN(),                          // CIN
                patient.getNom(),                          // Nom
                patient.getPrenom(),                       // Prenom
                dtf.format(patient.getDate_naissance()),   // Date de naissance (formatted)
                patient.getAdresse(),                      // Adresse
                patient.getTelephone(),                    // Telephone
                patient.getEmail(),                        // Email
                patient.getSexe(),                         // Sexe
                patient.getAge(),                          // Age
                patient.getAssurence(),                    // Assurence
                patient.getRisque(),                       // Risque
                patient.getFacture_total() == null ? "nu" : String.format("%.2f", patient.getFacture_total()), // Facture total (nullable)
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(patient.getDate_ajout()) // Date d'ajout (formatted)
        );
    }

    protected static int generateId() throws DAOException {
        int lastId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("=");
                if (data.length > 2) {
                    lastId = Integer.parseInt(data[0]);
                }
            }
        } catch (FileNotFoundException e) {
            return 1;
        } catch (IOException e) {
            throw new DAOException("Failed to generate patient ID", e);
        }
        return lastId + 1;
    }
}