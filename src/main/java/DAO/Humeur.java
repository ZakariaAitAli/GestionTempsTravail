package DAO;

public class Humeur {


    private int id_humeur;
    private int humeur;

    // Constructeur par défaut
    public Humeur() {
    }

    // Constructeur avec paramètres
    public Humeur(int id_humeur, int humeur) {
        this.id_humeur = id_humeur;
        this.humeur = humeur;
    }

    // Getter pour l'ID de la pause
    public int getId_humeur() {
        return id_humeur;
    }

    // Setter pour l'ID de la pause
    public void setId_humeur(int id_humeur) {
        this.id_humeur = id_humeur;
    }

    // Getter pour la pause
    public int getHumeur() {
        return humeur;
    }

    // Setter pour la pause
    public void setHumeur(int humeur) {
        this.humeur = humeur;
    }
}
