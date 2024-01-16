package exam.sb.matchservice.domaine;

import java.util.Date;

public class Match {

    private int id;
    private String equipeDomicile;
    private String equipeExterieure;
    private int scoreEquipeDomicile;
    private int scoreEquipeExterieure;
    private Date dateMatch;
    private String lieu;

    // Constructeur par défaut nécessaire pour la désérialisation (par exemple, si vous utilisez JSON)
    public Match() {
    }

    public Match(int id, String equipeDomicile, String equipeExterieure, int scoreEquipeDomicile,
                 int scoreEquipeExterieure, Date dateMatch, String lieu) {
        this.id = id;
        this.equipeDomicile = equipeDomicile;
        this.equipeExterieure = equipeExterieure;
        this.scoreEquipeDomicile = scoreEquipeDomicile;
        this.scoreEquipeExterieure = scoreEquipeExterieure;
        this.dateMatch = dateMatch;
        this.lieu = lieu;
    }

    // Getters et Setters (vous pouvez les générer automatiquement avec votre IDE)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipeDomicile() {
        return equipeDomicile;
    }

    public void setEquipeDomicile(String equipeDomicile) {
        this.equipeDomicile = equipeDomicile;
    }

    public String getEquipeExterieure() {
        return equipeExterieure;
    }

    public void setEquipeExterieure(String equipeExterieure) {
        this.equipeExterieure = equipeExterieure;
    }

    public int getScoreEquipeDomicile() {
        return scoreEquipeDomicile;
    }

    public void setScoreEquipeDomicile(int scoreEquipeDomicile) {
        this.scoreEquipeDomicile = scoreEquipeDomicile;
    }

    public int getScoreEquipeExterieure() {
        return scoreEquipeExterieure;
    }

    public void setScoreEquipeExterieure(int scoreEquipeExterieure) {
        this.scoreEquipeExterieure = scoreEquipeExterieure;
    }

    public Date getDateMatch() {
        return dateMatch;
    }

    public void setDateMatch(Date dateMatch) {
        this.dateMatch = dateMatch;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
}
