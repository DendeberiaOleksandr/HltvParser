package com.dendeberia.domain;

public class Data {
    private Stat[] stats;

    public Data() {
    }

    public Data(Stat[] stats) {
        this.stats = stats;
    }

    public Stat[] getStats() {
        return stats;
    }

    public void setStats(Stat[] stats) {
        this.stats = stats;
    }
}
