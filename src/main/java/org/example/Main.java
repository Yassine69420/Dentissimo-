package org.example;

import org.example.Controllers.PatientController;
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

import static org.example.MODELS.enums.Assurence.CNSS;


public class Main {
    public static void main(String[] args) {
        PatientController ahmed = new PatientController(new PatientService(new PatientDAO()));
        try{

            Patient patient = new Patient(
                    1,
                    "C239823",
                    "Achraf",
                    "Hakimi",
                    LocalDate.of(2003, 2, 2),
                    "Rabat sale",
                    "0823823",
                    "2132@gmail.com",
                    "Homme",
                    22,
                    CNSS,
                    "Maladies Chroniques",
                    0.0,
                    LocalDateTime.of(2025, 1, 16, 10, 0)
            );
//            System.out.println(patient);

           ahmed.addPatient(patient);
        }catch (Exception e){
            System.out.println(e);
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

