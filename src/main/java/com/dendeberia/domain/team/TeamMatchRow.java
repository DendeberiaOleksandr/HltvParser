package com.dendeberia.domain.team;

import com.dendeberia.domain.Map;
import com.dendeberia.domain.MapScore;
import com.dendeberia.domain.TeamWrapper;
import com.dendeberia.domain.event.EventWrapper;

import java.util.Date;

public class TeamMatchRow {
    private Date date;
    private EventWrapper eventWrapper;
    private TeamWrapper opponent;
    private Map map;
    private MapScore mapScore;
    private boolean won;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EventWrapper getEventWrapper() {
        return eventWrapper;
    }

    public void setEventWrapper(EventWrapper eventWrapper) {
        this.eventWrapper = eventWrapper;
    }

    public TeamWrapper getOpponent() {
        return opponent;
    }

    public void setOpponent(TeamWrapper opponent) {
        this.opponent = opponent;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public MapScore getMapScore() {
        return mapScore;
    }

    public void setMapScore(MapScore mapScore) {
        this.mapScore = mapScore;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }
}
