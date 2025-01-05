package org.example;

import org.example.MODELS.Patient;
import org.example.Repositories.Implementation.PatientDAO;

import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


            PatientDAO patientcontroller = new PatientDAO();

            Patient patient1 = new Patient(
                    "CIN12345", "test", "Hassan", dateFormat.parse("1990-05-15"),
                    "123 Rue Principal", "0612345678", "ali.hassan@example.com", "Male",
                    34
            );

            Patient patient2 = new Patient(
                    "CIN54321", "Fatima", "Zahra", dateFormat.parse("1995-08-20"),
                    "456 Rue Atlas", "0678543210", "fatima.zahra@example.com", "Female",
                    29
            );

            Patient patient3 = new Patient(
                    "CIN67890", "Mohamed", "Said", dateFormat.parse("1987-03-10"),
                    "789 Rue Souk", "0611112222", "mohamed.said@example.com", "Male",
                     37
            );


            Patient patient4 = new Patient(
                    "CIN98765", "Amina", "Bouchra", dateFormat.parse("1993-11-25"),
                    "1010 Rue Sidi", "0622334455", "amina.bouchra@example.com", "Female",
                     31
            );

            Patient patient5 = new Patient(
                    "CIN24680", "testaaaaaaaaaaaa", "El Mekki", dateFormat.parse("2000-01-01"),
                    "2020 Rue Tafilalet", "0600001111", "yassine.mekki@example.com", "Male",
                    24
            );

//            patientcontroller.add(patient1);
//            patientcontroller.add(patient2);
//            patientcontroller.add(patient3);
//            patientcontroller.add(patient4);
//            patientcontroller.add(patient5);

//            patientcontroller.update(new Patient("C12039","Mahmood","benana",dateFormat.parse("2000-01-01"),"sale al jadida","0677889910","hamdaoui@gmail.ma","shemale",1,28,28000,dateFormat.parse("2000-01-01"))
//            );
//            patientcontroller.getAll();
//            patientcontroller.delete(1);
//            patientcontroller.getById(1);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
