package com.dendeberia.domain.event;

import com.dendeberia.domain.Location;
import com.dendeberia.domain.TeamWrapper;
import com.dendeberia.domain.event.mvp.EventMvpWrapper;
import com.dendeberia.domain.event.placement.PrizeDistributionWrapper;
import com.dendeberia.domain.event.result.ResultDayWrapper;

import java.util.Date;
import java.util.List;

public class Event {
    private String name;
    private String imgUrl;
    private List<TeamWrapper> teams;
    private Date startDate;
    private Date endDate;
    private String prizePool;
    private Location location;
    private EventMvpWrapper eventMvp;
    private PrizeDistributionWrapper prizeDistributionWrapper;
    private List<ResultDayWrapper> results;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<TeamWrapper> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamWrapper> teams) {
        this.teams = teams;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPrizePool() {
        return prizePool;
    }

    public void setPrizePool(String prizePool) {
        this.prizePool = prizePool;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public EventMvpWrapper getEventMvp() {
        return eventMvp;
    }

    public void setEventMvp(EventMvpWrapper eventMvp) {
        this.eventMvp = eventMvp;
    }

    public PrizeDistributionWrapper getPrizeDistributionWrapper() {
        return prizeDistributionWrapper;
    }

    public void setPrizeDistributionWrapper(PrizeDistributionWrapper prizeDistributionWrapper) {
        this.prizeDistributionWrapper = prizeDistributionWrapper;
    }

    public List<ResultDayWrapper> getResults() {
        return results;
    }

    public void setResults(List<ResultDayWrapper> results) {
        this.results = results;
    }
}
