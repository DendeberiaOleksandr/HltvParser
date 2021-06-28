package com.dendeberia.domain.team;

import com.dendeberia.domain.TeamWrapper;
import com.dendeberia.domain.event.EventWrapper;

public class EventHistoryRow {
    private EventWrapper eventWrapper;
    private String place;
    private TeamWrapper teamWrapper;

    public EventWrapper getEventWrapper() {
        return eventWrapper;
    }

    public void setEventWrapper(EventWrapper eventWrapper) {
        this.eventWrapper = eventWrapper;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public TeamWrapper getTeamWrapper() {
        return teamWrapper;
    }

    public void setTeamWrapper(TeamWrapper teamWrapper) {
        this.teamWrapper = teamWrapper;
    }
}
