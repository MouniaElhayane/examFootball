package com.example.statsservice.domaine;

public class PlayerStats {
    private String playerId;
    private int goalsScored;
    private int assists;
    private int yellowCards;
    private int redCards;

    public PlayerStats(String playerId, int goalsScored, int assists, int yellowCards, int redCards) {
        this.playerId = playerId;
        this.goalsScored = goalsScored;
        this.assists = assists;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }

    // Ajoutez d'autres méthodes selon vos besoins

    public void addGoal() {
        this.goalsScored++;
    }

    public void addAssist() {
        this.assists++;
    }
}
