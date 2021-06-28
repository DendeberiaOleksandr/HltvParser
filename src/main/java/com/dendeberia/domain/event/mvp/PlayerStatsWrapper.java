package com.dendeberia.domain.event.mvp;

public class PlayerStatsWrapper {
    private double killsPerRound;
    private double deathsPerRound;
    private String killAssistSurvivedTraded;
    private double impact;
    private double damagePerRound;
    private double rating2;

    public double getKillsPerRound() {
        return killsPerRound;
    }

    public void setKillsPerRound(double killsPerRound) {
        this.killsPerRound = killsPerRound;
    }

    public double getDeathsPerRound() {
        return deathsPerRound;
    }

    public void setDeathsPerRound(double deathsPerRound) {
        this.deathsPerRound = deathsPerRound;
    }

    public String getKillAssistSurvivedTraded() {
        return killAssistSurvivedTraded;
    }

    public void setKillAssistSurvivedTraded(String killAssistSurvivedTraded) {
        this.killAssistSurvivedTraded = killAssistSurvivedTraded;
    }

    public double getImpact() {
        return impact;
    }

    public void setImpact(double impact) {
        this.impact = impact;
    }

    public double getDamagePerRound() {
        return damagePerRound;
    }

    public void setDamagePerRound(double damagePerRound) {
        this.damagePerRound = damagePerRound;
    }

    public double getRating2() {
        return rating2;
    }

    public void setRating2(double rating2) {
        this.rating2 = rating2;
    }
}
