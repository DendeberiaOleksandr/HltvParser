package com.dendeberia;

import com.dendeberia.domain.event.result.ResultDayWrapper;
import com.dendeberia.domain.event.result.ResultRowWrapper;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ResultParserTest {

    private ResultParser resultParser;
    private DateParser dateParser;
    private CommonParser commonParser;
    private List<ResultDayWrapper> resultDayWrappers;
    private ResultDayWrapper resultDayWrapper;
    private List<ResultRowWrapper> results;
    private ResultRowWrapper resultRowWrapper;

    @BeforeEach
    public void init(){
        this.commonParser = new CommonParser();
        this.dateParser = new DateParser(commonParser);
        this.resultParser = new ResultParser(commonParser, dateParser);
        this.resultDayWrappers =
                resultParser.parseResults("https://www.hltv.org/results?event=5219");
        this.resultDayWrapper = resultDayWrappers.get(0);
        this.results = resultDayWrapper.getResults();
        this.resultRowWrapper = results.get(0);
    }

    @Test
    @Order(1)
    public void parseResultDayWrappersShouldNotBeNullAndShouldContain7Wrappers(){
        Assertions.assertNotEquals(null, resultDayWrappers);
        Assertions.assertEquals(7, resultDayWrappers.size());
    }

    @Test
    @Order(2)
    public void parseResultDayWrappersDateShouldNotBeNull(){
        Assertions.assertNotEquals(null, resultDayWrapper.getDate());
    }

    @Test
    @Order(3)
    public void parseResultRowsShouldContain1Row(){
        Assertions.assertNotEquals(null, results);
        Assertions.assertEquals(1, results.size());
    }

    @Test
    @Order(4)
    public void parseResultRowWrapperShouldNotBeNull(){
        Assertions.assertNotEquals(null, resultRowWrapper);
    }

    @Test
    @Order(5)
    public void parseResultRowWrapperTeam1ScoreShouldBe0(){
        Assertions.assertEquals(0, resultRowWrapper.getTeam1Score());
    }

    @Test
    @Order(6)
    public void parseResultRowWrapperTeam2ScoreShouldBe3(){
        Assertions.assertEquals(3, resultRowWrapper.getTeam2Score());
    }

    @Test
    @Order(7)
    public void parseResultRowWrapperTeam1Name(){
        Assertions.assertEquals("OG", resultRowWrapper.getTeamWrapper1().getName());
    }

    @Test
    @Order(8)
    public void parseResultRowWrapperTeam2Name(){
        Assertions.assertEquals("Gambit", resultRowWrapper.getTeamWrapper2().getName());
    }

    @Test
    @Order(9)
    public void parseResultRowWrapperTeam1Img(){
        Assertions.assertEquals("https://img-cdn.hltv.org/teamlogo/" +
                "7b6DouMNGWcqENDx1vw_Ot.png?" +
                "ixlib=java-2.1.0&w=50&" +
                "s=5ffc85bfbc0398d0a826590a790a08a6",
                resultRowWrapper.getTeamWrapper1().getImgUrl());
    }

    @Test
    @Order(10)
    public void parseResultRowWrapperTeam2Img(){
        Assertions.assertEquals("https://img-cdn.hltv.org/teamlogo/" +
                "pNV-lVdpvYZIkDwHdEXXg-.svg?" +
                "ixlib=java-2.1.0&" +
                "s=8b557b5b4d283208976340ef1bc44c76",
                resultRowWrapper.getTeamWrapper2().getImgUrl());
    }

    @Test
    @Order(11)
    public void parseResultRowWrapperStarsShouldBe2(){
        Assertions.assertEquals(2, resultRowWrapper.getRating());
    }

    @Test
    @Order(12)
    public void parseResultRowWrapperMapShouldBeBo5(){
        Assertions.assertEquals("bo5", resultRowWrapper.getMap());
    }

}

