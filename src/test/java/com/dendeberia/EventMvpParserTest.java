package com.dendeberia;

import com.dendeberia.domain.PlayerWrapper;
import com.dendeberia.domain.event.mvp.EventMvpWrapper;
import com.dendeberia.domain.event.mvp.PlayerStatsWrapper;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EventMvpParserTest {
    private CommonParser commonParser;
    private EventMvpParser eventMvpParser;
    private EventMvpWrapper eventMvpWrapper;
    private PlayerStatsWrapper playerStatsWrapper;
    private PlayerWrapper playerWrapper;

    @BeforeEach
    public void init(){
        commonParser = new CommonParser();
        eventMvpParser = new EventMvpParser(commonParser);
        eventMvpWrapper = eventMvpParser.parseEventMvpWrapper("https://www.hltv.org/events/5219/iem-summer-2021");
        playerStatsWrapper = eventMvpWrapper.getPlayerStatsWrapper();
        playerWrapper = eventMvpWrapper.getPlayer();
    }

    @Test
    @Order(1)
    public void parseEventMvpWrapperShouldNotBeNull(){
        Assertions.assertNotEquals(null, eventMvpWrapper);
    }

    @Test
    @Order(2)
    public void parseEventMvpWrapperCoinImageUrl(){
        Assertions.assertEquals("https://www.hltv.org/img/static/event/mvpOld.png",
                eventMvpWrapper.getCoinImgUrl());
    }

    @Test
    @Order(3)
    public void parseEventMvpWrapperPlayerShouldNotBeNull(){
        Assertions.assertNotEquals(null, playerWrapper);
    }

    @Test
    @Order(4)
    public void parseEventMvpWrapperPlayerName(){
        Assertions.assertEquals("Sergey Ax1Le Rykhtorov", playerWrapper.getName());
    }

    @Test
    @Order(5)
    public void parseEventMvpWrapperPlayerFlag(){
        Assertions.assertEquals("https://www.hltv.org/img/static/flags/30x20/RU.gif",
                playerWrapper.getCountryImgUrl());
    }

    @Test
    @Order(6)
    public void parseEventMvpWrapperPlayerImageUrl(){
        Assertions.assertEquals("https://img-cdn.hltv.org/playerbodyshot/" +
                        "-8a_oSWDKqq0GJo6Zdnz9p.png?bg=3e4c54&h=800&ixlib=" +
                        "java-2.1.0&rect=124%2C12%2C467%2C467&w=800" +
                        "&s=63d1f393008b96850dbfd825723e6140",
                playerWrapper.getImgUrl());
    }

    @Test
    @Order(7)
    public void parseEventMvpPlayerStatsShouldNotBeNull(){
        Assertions.assertNotEquals(null, playerStatsWrapper);
    }

    @Test
    @Order(8)
    public void parseEventMvpPlayerStatsKillsPerRound(){
        Assertions.assertEquals(0.84, playerStatsWrapper.getKillsPerRound());
    }

    @Test
    @Order(9)
    public void parseEventMvpPlayerStatsDeathsPerRound(){
        Assertions.assertEquals(0.59, playerStatsWrapper.getDeathsPerRound());
    }

    @Test
    @Order(10)
    public void parseEventMvpPlayerStatsKllAssistSurvivedTraded(){
        Assertions.assertEquals("74.4%", playerStatsWrapper.getKillAssistSurvivedTraded());
    }

    @Test
    @Order(11)
    public void parseEventMvpPlayerStatsImpact(){
        Assertions.assertEquals(1.49, playerStatsWrapper.getImpact());
    }

    @Test
    @Order(12)
    public void parseEventMvpPlayerStatsDamagePerRound(){
        Assertions.assertEquals(86.3, playerStatsWrapper.getDamagePerRound());
    }

    @Test
    @Order(13)
    public void parseEventMvpPlayerStatsRating2(){
        Assertions.assertEquals(1.33, playerStatsWrapper.getRating2());
    }
}
