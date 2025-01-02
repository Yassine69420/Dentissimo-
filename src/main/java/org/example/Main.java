package org.example;

import org.example.MODELS.Patient;
import org.example.Repositories.Exceptions.DAOException;
import org.example.Repositories.Implementation.PatientDAOimp;

import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Creating 5 patients with generated IDs
            PatientDAOimp hi = new PatientDAOimp();

            Patient patient1 = new Patient(
                    "CIN12345", "Ali", "Hassan", dateFormat.parse("1990-05-15"),
                    "123 Rue Principal", "0612345678", "ali.hassan@example.com", "Male",
                    hi.generateId(), 34
            );
            hi.addPatient(patient1);

            Patient patient2 = new Patient(
                    "CIN54321", "Fatima", "Zahra", dateFormat.parse("1995-08-20"),
                    "456 Rue Atlas", "0678543210", "fatima.zahra@example.com", "Female",
                    hi.generateId(), 29
            );
            hi.addPatient(patient2);
            Patient patient3 = new Patient(
                    "CIN67890", "Mohamed", "Said", dateFormat.parse("1987-03-10"),
                    "789 Rue Souk", "0611112222", "mohamed.said@example.com", "Male",
                    hi.generateId(), 37
            );
            hi.addPatient(patient3);

            Patient patient4 = new Patient(
                    "CIN98765", "Amina", "Bouchra", dateFormat.parse("1993-11-25"),
                    "1010 Rue Sidi", "0622334455", "amina.bouchra@example.com", "Female",
                    hi.generateId(), 31
            );
            hi.addPatient(patient4);
            Patient patient5 = new Patient(
                    "CIN24680", "testaaaaaaaaaaaa", "El Mekki", dateFormat.parse("2000-01-01"),
                    "2020 Rue Tafilalet", "0600001111", "yassine.mekki@example.com", "Male",
                    hi.generateId(), 24
            );
            hi.addPatient(patient5);
            // Uncomment the desired operations:






//            hi.updatePatient(patient5);
//            hi.getAllPatients();

//            hi.deletePatient("P005");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
