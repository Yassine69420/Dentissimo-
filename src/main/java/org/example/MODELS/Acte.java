package org.example.MODELS;

public class Acte {
    private int idActe;
    private String typeActe;
    private String description;
    private float cost;

    public Acte(int idActe, String typeActe, String description, float cost) {
        this.idActe = idActe;
        this.typeActe = typeActe;
        this.description = description;
        this.cost = cost;
    }

    public Acte( String typeActe, String description, float cost) {
        this.idActe = idActe;
        this.typeActe = typeActe;
        this.description = description;
        this.cost = cost;
    }

    public int getIdActe() {
        return idActe;
    }

    public void setIdActe(int idActe) {
        this.idActe = idActe;
    }

    public String getTypeActe() {
        return typeActe;
    }

    public void setTypeActe(String typeActe) {
        this.typeActe = typeActe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Acte{" +
                "idActe=" + idActe +
                ", typeActe='" + typeActe + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }
}
