package com.dendeberia.domain;

import com.dendeberia.domain.event.EventWrapper;
import com.dendeberia.domain.team.*;

import java.util.List;

public class Team {
    private Long id;
    private List<TeamPlayerWrapper> currentPlayers;
    private List<TeamPlayerWrapper> historicPlayers;
    private List<TeamPlayerWrapper> standinPlayers;
    private String name;
    private String imgUrl;
    private String countryName;
    private String countryImgUrl;
    private int worldRanking;
    private double averagePlayerAge;
    private PlayerWrapper coach;
    private List<EventWrapper> wonEvents;
    private int weeksInTop30;
    private MapResult mapResult;
    private long totalKills;
    private long totalDeaths;
    private long roundsPlayed;
    private double kd;
    private java.util.Map<Map, Integer> mapDistribution;
    private java.util.Map<Map, String> mapHighlight;
    private List<TeamMapOverview> teamMapOverviews;
    private List<EventHistoryRow> eventHistoryRows;
    private List<TeamLineup> lineups;
    private List<TeamMatchRow> matches;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
