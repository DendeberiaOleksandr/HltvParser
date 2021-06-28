package com.dendeberia.domain.event.placement;

import com.dendeberia.domain.TeamWrapper;

public class PlacementWrapper {
    private TeamWrapper teamWrapper;
    private String place;
    private String prize;

    public TeamWrapper getTeamWrapper() {
        return teamWrapper;
    }

    public void setTeamWrapper(TeamWrapper teamWrapper) {
        this.teamWrapper = teamWrapper;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }
}
