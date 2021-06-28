package com.dendeberia;

import com.dendeberia.domain.TeamWrapper;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EventTeamsParserTest {

    private CommonParser commonParser;
    private EventTeamsParser parser;
    private List<TeamWrapper> teams;
    private TeamWrapper team;

    @BeforeEach
    public void init(){
        commonParser = new CommonParser();
        parser = new EventTeamsParser(commonParser);
        teams = parser.parseTeams("https://www.hltv.org/events/5604/blast-premier-spring-final-2021");
        team = teams.get(0);
    }

    @Test
    @Order(1)
    public void parseEventTeamsSize(){
        Assertions.assertEquals(8, teams.size());
    }

    @Test
    @Order(2)
    public void parseEventTeamsFirstTeamName(){
        Assertions.assertEquals("Gambit", team.getName());
    }

    @Test
    @Order(3)
    public void parseEventTeamsFirstTeamImageUrl(){
        Assertions.assertEquals("https://img-cdn.hltv.org/teamlogo/" +
                "pNV-lVdpvYZIkDwHdEXXg-.svg?" +
                "ixlib=java-2.1.0" +
                "&s=8b557b5b4d283208976340ef1bc44c76",
                team.getImgUrl());
    }
}
