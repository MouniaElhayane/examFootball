package com.example.statsservice.domaine;

public class TeamStats {
    private String teamId;
    private int matchesPlayed;
    private int wins;
    private int draws;
    private int losses;

    public TeamStats(String teamId, int matchesPlayed, int wins, int draws, int losses) {
        this.teamId = teamId;
        this.matchesPlayed = matchesPlayed;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    // Ajoutez d'autres méthodes selon vos besoins

    public void addWin() {
        this.wins++;
    }

    // Ajoutez les getters et setters
}