package org.example;

import org.example.MODELS.Rendezvous;
import org.example.Repositories.Exceptions.DAOException;
import org.example.Repositories.Implementation.RendezVousDAO;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws DAOException {
        RendezVousDAO dao = new RendezVousDAO();

        try {
            // Create Rendezvous objects
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date date = sdf.parse("10-01-2025");
            Time time = Time.valueOf("09:30:00");

            Rendezvous rendezvous1 = new Rendezvous(date, time, 1, 101);
            Rendezvous rendezvous2 = new Rendezvous(sdf.parse("12-01-2025"), Time.valueOf("11:00:00"), 2, 102);

            // 1. Test ADD method
//            dao.add(rendezvous1);
//            dao.add(rendezvous2);

            System.out.println("Rendezvous added successfully!");
//
//             2. Test GET ALL method
//            List<Rendezvous> rendezvousList = dao.getAll();
//            System.out.println("All Rendezvous:");
//            for (Rendezvous rdv : rendezvousList) {
//                System.out.println(rdv.getId_rdv() + " | " + rdv.getDate() + " | " + rdv.getHeure() + " | " +
//                        rdv.getPatient_id() + " | " + rdv.getMedecin_id());
//            }
////
            // 3. Test GET BY ID method
//            Rendezvous fetchedRdv = dao.getById(5);
//            System.out.println("Rendezvous with ID 5: " + fetchedRdv.getDate() + ", " + fetchedRdv.getHeure());

//            // 4. Test UPDATE method
//            Rendezvous Updatetest = dao.getById(5);
//            Updatetest.setHeure(Time.valueOf("22:00:00")); // Change time
//            dao.update(Updatetest);
//            System.out.println("Rendezvous updated!");

//            // Verify update
//            Rendezvous updatedRdv = dao.getById(1);
//            System.out.println("Updated Rendezvous Time: " + updatedRdv.getHeure());

//            // 5. Test DELETE method
//            dao.delete(1);
//            System.out.println("Rendezvous with ID 1 deleted!");

//            // Verify deletion
//            List<Rendezvous> remainingRendezvous = dao.getAll();
//            System.out.println("Remaining Rendezvous:");
//            for (Rendezvous rdv : remainingRendezvous) {
//                System.out.println(rdv.getId_rdv());
//            }

        } catch (ParseException e) {
            throw new DAOException("fuck you");
        }
    }
}
