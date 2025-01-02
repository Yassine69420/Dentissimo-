package org.example.Repositories.Implementation;

import org.example.MODELS.Patient;
import org.example.Repositories.Exceptions.DAOException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class PatientDAOimp  {
    private static final String Path = "src/main/resources/Patient.csv";

    // Add a patient
    public void addPatient(Patient patient) throws DAOException {
        try (BufferedWriter bwriter = new BufferedWriter(new FileWriter(Path, true))) {
            bwriter.write(formatPatientData(patient));

        } catch (IOException e) {
            throw new DAOException("Failed to add patient", e);
        }
    }

    // Retrieve all patients
    public void getAllPatients() throws DAOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(Path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            throw new DAOException("Failed to retrieve patients", e);
        }
    }
//update
// update patient
    public void updatePatient(Patient patient) throws DAOException {
        String tempFile = "src/main/resources/temp_Patient.csv";
        File inputFile = new File(Path);
        File tempFileObj = new File(tempFile);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileObj))) {

            String line;
            boolean updated = false;

            // Read through the file line by line
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3 && data[2].equals(String.valueOf(patient.getPatient_id()))) {
                    // Update the patient's record
                    writer.write(formatPatientData(patient));
                    updated = true;
                } else {
                    // Write the existing line to the temporary file
                    writer.write(line + "\n");
                }
            }

            // If no matching patient was found, throw an exception
            if (!updated) {
                throw new DAOException("Patient with ID " + patient.getPatient_id() + " not found.");
            }

        } catch (IOException e) {
            throw new DAOException("Failed to update patient", e);
        }

        // Replace the original file with the temporary file
        if (!inputFile.delete()) {
            throw new DAOException("Failed to delete the original file.");
        }
        if (!tempFileObj.renameTo(inputFile)) {
            throw new DAOException("Failed to rename the temporary file to the original file name.");
        }
    }

// Helper method to format patient data into CSV format
    private String formatPatientData(Patient patient) {
        return String.format("%s,%s,%d,%d,%s,%s,%s,%s,%s\n",
                patient.getNom(),
                patient.getPrenom(),
                patient.getPatient_id(),
                patient.getAge(),
                patient.getSexe(),
                patient.getCIN(),
                patient.getEmail(),
                patient.getAdresse(),
                patient.getDate_ajout());
    }

    // Delete patient
    public void deletePatient(String patient_id) throws DAOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Path));
            StringBuilder input = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (!data[2].equals(patient_id)) { // Compare by patient_id
                    input.append(line).append("\n");
                }
            }
            reader.close();

            try (FileOutputStream fileOut = new FileOutputStream(Path)) {
                fileOut.write(input.toString().getBytes());
            }
        } catch (IOException e) {
            throw new DAOException("Failed to delete patient", e);
        }
    }
//generate id
    public static int generateId() throws DAOException {
        int lastId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(Path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 2) { // Ensure valid line structure
                    lastId = Integer.parseInt(data[2]); // Patient ID is at index 2
                }
            }
        } catch (IOException e) {
            throw new DAOException("Failed to generate patient ID", e);
        }
        // Increment the last ID and return it
        return lastId + 1;
    }
}
