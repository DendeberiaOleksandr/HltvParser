package com.dendeberia.domain.event;

import com.dendeberia.domain.TeamWrapper;

import java.util.Date;

public class BracketNode {
    private TeamWrapper upTeam;
    private TeamWrapper downTeam;
    private TeamWrapper teamWinner;
    private int upScore;
    private int downScore;
    private Date date;

    public int getUpScore() {
        return upScore;
    }

    public void setUpScore(int upScore) {
        this.upScore = upScore;
    }

    public int getDownScore() {
        return downScore;
    }

    public void setDownScore(int downScore) {
        this.downScore = downScore;
    }

    public TeamWrapper getUpTeam() {
        return upTeam;
    }

    public void setUpTeam(TeamWrapper upTeam) {
        this.upTeam = upTeam;
    }

    public TeamWrapper getDownTeam() {
        return downTeam;
    }

    public void setDownTeam(TeamWrapper downTeam) {
        this.downTeam = downTeam;
    }

    public TeamWrapper getTeamWinner() {
        return teamWinner;
    }

    public void setTeamWinner(TeamWrapper teamWinner) {
        this.teamWinner = teamWinner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BracketNode{" +
                "upTeam=" + upTeam +
                ", downTeam=" + downTeam +
                ", teamWinner=" + teamWinner +
                ", upScore=" + upScore +
                ", downScore=" + downScore +
                ", date=" + date +
                '}';
    }
}
