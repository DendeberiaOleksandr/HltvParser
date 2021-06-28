package com.dendeberia.domain.team;

import com.dendeberia.domain.Map;
import com.dendeberia.domain.TeamWrapper;

public class TeamMapOverview {
    private Map map;
    private int wins;
    private int draws;
    private int losses;
    private double winrate;
    private long rounds;
    private double roundWinAfterGettingFirstKill;
    private double roundWinAfterReceivingFirstDeath;
    private TeamWrapper biggestWinTeam;
    private int biggestWinEnemyTeamScore;
    private int biggestWinScore;
    private TeamWrapper biggestDefeatTeam;
    private int biggestDefeatScore;
    private int biggestDefeatEnemyTeamScore;
}
