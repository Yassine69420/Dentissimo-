package org.example.Repositories.Implementation;

import org.example.MODELS.Patient;
import org.example.Repositories.Exceptions.DAOException;
import org.example.Repositories.Interfaces.IPatientDAO;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO implements IPatientDAO {
    private static final String PATH = "src/main/resources/Patient.csv";

    // Add a patient
    @Override
    public void add(Patient patient) throws DAOException {
        int newId = generateId();
        patient.setPatient_id(newId);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH, true))) {
            writer.write(formatPatientData(patient));
            writer.newLine();
        } catch (IOException e) {
            throw new DAOException("Failed to add patient", e);
        }
    }

    // Retrieve all patients
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
        for(Patient patient:patients){
            System.out.println(patient.getNom());
        }
        return patients;

    }

    @Override
    public List<Patient> getAllPatientsbyname(String search) throws DAOException {
        List<Patient> patients = getAll();
        List<Patient> filteredPatients = new ArrayList<>();
        for (Patient patient : patients) {
            if (patient.getNom().equalsIgnoreCase(search)) {
                filteredPatients.add(patient);
            }
        }
        return filteredPatients;
    }

    @Override
    public List<Patient> getAllPatientsbyage(int age) throws DAOException {
        List<Patient> patients = getAll();
        List<Patient> filteredPatients = new ArrayList<>();
        for (Patient patient : patients) {
            if (patient.getAge() == age) {
                filteredPatients.add(patient);
            }
        }
        return filteredPatients;
    }

    @Override
    public List<Patient> getAllPatientsbysexe(String sexe) throws DAOException {
        List<Patient> patients = getAll();
        List<Patient> filteredPatients = new ArrayList<>();
        for (Patient patient : patients) {
            if (patient.getSexe().equalsIgnoreCase(sexe)) {
                filteredPatients.add(patient);
            }
        }
        return filteredPatients;
    }

    @Override
    public void update(Patient patient) throws DAOException {
        String tempFile = "src/main/resources/temp_Patient.csv";
        File inputFile = new File(PATH);
        File tempFileObj = new File(tempFile);
        String line;
        boolean updated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileObj))) {

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3 && Integer.parseInt(data[2]) == patient.getPatient_id()) {
                    writer.write(formatPatientData(patient));
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
                String[] data = line.split(",");
                if (data.length >= 3 && Integer.parseInt(data[2]) == patient_id) {
                    deleted = true;
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            if (!deleted) {
                throw new DAOException("Patient with ID " + patient_id + " not found.");
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

    // Helper method to format patient data into CSV format
    private String formatPatientData(Patient patient) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("%s,%s,%d,%d,%s,%s,%s,%s,%s",
                patient.getNom(),
                patient.getPrenom(),
                patient.getPatient_id(),
                patient.getAge(),
                patient.getSexe(),
                patient.getCIN(),
                patient.getTelephone(),
                patient.getAdresse(),
                sdf.format(patient.getDate_ajout()));
    }

    // Helper method to parse patient data from a CSV line
    private Patient parsePatientData(String line) throws DAOException {
        try {
            String[] data = line.split(",");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");

            return new Patient(
                    data[5], // CIN
                    data[0], // Nom
                    data[1], // Prenom
                    sdf.parse(data[8]), // Date ajout
                    data[7], // Adresse
                    data[6], // Telephone
                    data[4], // Email
                    data[3], // Sexe
                    Integer.parseInt(data[3])  // Age
            );
        } catch (Exception e) {
            throw new DAOException("Failed to parse patient data: " + line, e);
        }
    }

    public static int generateId() throws DAOException {
        int lastId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 2) {
                    lastId = Integer.parseInt(data[2]);
                }
            }
        } catch (FileNotFoundException e) {
            // File not found, this means no patients exist yet
            return 1;
        } catch (IOException e) {
            throw new DAOException("Failed to generate patient ID", e);
        }
        return lastId + 1;
    }
}
