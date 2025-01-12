package org.example.MODELS;


import org.example.MODELS.enums.VisitType;

import java.time.LocalDateTime;
import java.util.List;

public class Visite {
    private int idVisite;
    private VisitType typeVisite;
    private int idPatient;
    private int idMedecin;
    private String notes;
    private LocalDateTime dateVisite;
    private List<Integer> dentsConcernees;
    private List<Acte> actes;
    private float cost;

    public int getIdVisite() {
        return idVisite;
    }

    public void setIdVisite(int idVisite) {
        this.idVisite = idVisite;
    }

    public VisitType getTypeVisite() {
        return typeVisite;
    }

    public void setTypeVisite(VisitType typeVisite) {
        this.typeVisite = typeVisite;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public int getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(int idMedecin) {
        this.idMedecin = idMedecin;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getDateVisite() {
        return dateVisite;
    }

    public void setDateVisite(LocalDateTime dateVisite) {
        this.dateVisite = dateVisite;
    }

    public List<Integer> getDentsConcernees() {
        return dentsConcernees;
    }

    public void setDentsConcernees(List<Integer> dentsConcernees) {
        this.dentsConcernees = dentsConcernees;
    }

    public List<Acte> getActes() {
        return actes;
    }

    public void setActes(List<Acte> actes) {
        this.actes = actes;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Visite{" +
                "idVisite=" + idVisite +
                ", typeVisite=" + typeVisite +
                ", idPatient=" + idPatient +
                ", idMedecin=" + idMedecin +
                ", notes='" + notes + '\'' +
                ", dateVisite=" + dateVisite +
                ", dentsConcernees=" + dentsConcernees +
                ", actes=" + actes +
                ", cost=" + cost +
                '}';
    }
}
