package com.dendeberia.domain.event.result;

import com.dendeberia.domain.TeamWrapper;

public class ResultRowWrapper {
    private TeamWrapper teamWrapper1;
    private  TeamWrapper teamWrapper2;
    private short team1Score = 0;
    private short team2Score = 0;
    private short rating;
    private String map;

    public TeamWrapper getTeamWrapper1() {
        return teamWrapper1;
    }

    public void setTeamWrapper1(TeamWrapper teamWrapper1) {
        this.teamWrapper1 = teamWrapper1;
    }

    public TeamWrapper getTeamWrapper2() {
        return teamWrapper2;
    }

    public void setTeamWrapper2(TeamWrapper teamWrapper2) {
        this.teamWrapper2 = teamWrapper2;
    }

    public short getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(short team1Score) {
        this.team1Score = team1Score;
    }

    public short getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(short team2Score) {
        this.team2Score = team2Score;
    }

    public short getRating() {
        return rating;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }
}
