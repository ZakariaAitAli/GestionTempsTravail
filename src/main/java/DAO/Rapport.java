package DAO;

public class Rapport {
    private int id_rapport;
    private int id_employee;
    private String path;
    private String date_generation;

    private int worktime_total;

    public int getId_rapport() {
        return id_rapport;
    }

    public void setId_rapport(int id_rapport) {
        this.id_rapport = id_rapport;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDate_generation() {
        return date_generation;
    }

    public void setDate_generation(String date_generation) {
        this.date_generation = date_generation;
    }

    public double getWorktime_total() {
        return worktime_total;
    }

    public void setWorktime_total(int worktime_total) {
        this.worktime_total = worktime_total;
    }
}
