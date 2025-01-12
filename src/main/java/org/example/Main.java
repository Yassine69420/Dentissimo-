package org.example;

import org.example.MODELS.Acte;
import org.example.MODELS.Medecin;
import org.example.MODELS.Patient;
import org.example.MODELS.Rendezvous;
import org.example.Exceptions.DAOException;
import org.example.Repositories.Implementation.ActeDAO;
import org.example.Repositories.Implementation.MedecinDAO;
import org.example.Repositories.Implementation.PatientDAO;
import org.example.Repositories.Implementation.RendezVousDAO;
import org.example.Services.Implementation.PatientService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ActeDAO acteDAO = new ActeDAO();

        try {
            // Add new Actes
            Acte acte1 = new Acte("Consultation", "General consultation", 150.0f);
            Acte acte2 = new Acte("Radiology", "X-ray imaging", 500.0f);
            Acte acte3 = new Acte("Surgery", "Minor surgery operation", 2500.0f);

//            acteDAO.add(acte1);
//            acteDAO.add(acte2);
//            acteDAO.add(acte3);
//
//            System.out.println("Actes added successfully!");

//            // Retrieve all Actes
            List<Acte> actes = acteDAO.getAll();
            System.out.println("\nAll Actes:");
            actes.forEach(System.out::println);
////
//            // Retrieve an Acte by ID
//            int searchId = 2;
//            Acte acteById = acteDAO.getById(searchId);
//            System.out.println("\nActe retrieved by ID (" + searchId + "): " + acteById);
//
//            // Update an Acte
//            Acte updatedActe = new Acte(1, "Radiology", "MRI Scan", 1200.0f);
//            acteDAO.update(updatedActe);
//            System.out.println("\nActe updated successfully!");
//
//            // Retrieve updated Actes
//            System.out.println("\nAll Actes after update:");
//            actes = acteDAO.getAll();
//            actes.forEach(System.out::println);
//
//            // Delete an Acte by ID
//            int deleteId = 1;
//            acteDAO.delete(deleteId);
//            System.out.println("\nActe with ID " + deleteId + " deleted successfully!");
//
//            // Retrieve all Actes after deletion
//            System.out.println("\nAll Actes after deletion:");
//            actes = acteDAO.getAll();
//            actes.forEach(System.out::println);

        } catch (DAOException e) {
            System.err.println("An error occurred: " + e.getMessage());

        }

    }

}
        //dd-MM-yyyy HH:mm
//        SimpleDateFormat sdfH = new SimpleDateFormat("dd-MM-yyyy HH:mm");
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");


        // Create Medecin objects
//            Medecin medecin1 = medecinDAO.getById(1);
//            Medecin medecin2 = medecinDAO.getById(2);
//            Medecin medecin3 = medecinDAO.getById(3);
//            System.out.println(medecin1.toString());
//            System.out.println(medecin2.toString());
//            System.out.println(medecin3.toString());

//            // Create Patient objects
//            Patient patient1 = new Patient(
//                    "CIN001",
//                    "John",
//                    "Doe",
//                   LocalDate.of(1990, 5, 15),  // Example date of birth
//                    "123 Street City",
//                    "123456789",
//                    "john.doe@example.com",
//                    "Male",
//                    35,
//                    250.75f,
//                    LocalDate.now()  // Date of addition
//            );

//Pservice.add(patient1);


//        MedecinDAO mado = new MedecinDAO();
//        System.out.println(mado.getByEmailandPass("yassine@gmail.com", "Password").toString());
//    }
//}
//            System.out.println(patientDAO.getById(1).getNom());
//            patientDAO.delete(1);
//            patientDAO.add(patient1);
//            patientDAO.add(patient2);
//            patientDAO.add(patient3);

//            List<Patient> patients = patientDAO.getAll();
//            for (Patient patient : patients) {
//                System.out.println(patient.toString());
//            }

//            Patient patient1 = patientDAO.getById(1);
//            Patient patient2 = patientDAO.getById(2);
//            Patient patient3 = patientDAO.getById(3);
//            System.out.println(patient1.toString());
//            System.out.println(patient2.toString());
//            System.out.println(patient3.toString());

            // Create Rendezvous objectsM

            // 1. Test ADD method
//            System.out.println("Adding rendezvous...");
//            dao.add(rendezvous1);
//            dao.add(rendezvous2);
//            dao.add(rendezvous3);
//            System.out.println("Rendezvous added successfully!");

//            // 2. Test GET ALL method
//            System.out.println("\nFetching all rendezvous...");
//            List<Rendezvous> rendezvousList = dao.getAll();
//            for (Rendezvous rdv : rendezvousList) {
//                System.out.println(rdv.getId_rdv() + " | " + sdf.format(rdv.getDate()) +
//                        " | Patient: " + rdv.getPatient().getNom() +
//                        " | Medecin: " + rdv.getMedecin().getNom());
//            }

//            // 3. Test GET BY ID method
//            System.out.println("\nFetching rendezvous by ID...");
//            Rendezvous fetchedRdv = dao.getById(1);
//            System.out.println("Rendezvous with ID 1: " + sdf.format(fetchedRdv.getDate()) +
//                    " | Patient: " + fetchedRdv.getPatient().getNom());
//
//            // 4. Test UPDATE method
//            System.out.println("\nUpdating rendezvous...");
//            fetchedRdv.setDate(sdf.parse("15-01-2025")); // Change date
//            dao.update(fetchedRdv);
//            System.out.println("Rendezvous updated!");
//
//            // 5. Test DELETE method
//            System.out.println("\nDeleting rendezvous...");
//            dao.delete(1);
//            System.out.println("Rendezvous with ID 1 deleted!");
//

//

