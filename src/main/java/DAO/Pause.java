package DAO;

public class Pause {
    private int id_pause;
    private int pause;

    // Constructeur par défaut
    public Pause() {
    }

    // Constructeur avec paramètres
    public Pause(int id_pause, int pause) {
        this.id_pause = id_pause;
        this.pause = pause;
    }

    // Getter pour l'ID de la pause
    public int getId_pause() {
        return id_pause;
    }

    // Setter pour l'ID de la pause
    public void setId_pause(int id_pause) {
        this.id_pause = id_pause;
    }

    // Getter pour la pause
    public int getPause() {
        return pause;
    }

    // Setter pour la pause
    public void setPause(int pause) {
        this.pause = pause;
    }
}
