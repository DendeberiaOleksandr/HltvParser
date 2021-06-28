package com.dendeberia;

import com.dendeberia.domain.PrizeDistributionParser;
import com.dendeberia.domain.event.placement.PlacementWrapper;
import com.dendeberia.domain.event.placement.PrizeDistributionWrapper;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PrizeDistributionParserTest {
    private CommonParser commonParser;
    private PrizeDistributionParser prizeDistributionParser;
    private PrizeDistributionWrapper prizeDistributionWrapper;
    private List<PlacementWrapper> placementWrappers;
    private PlacementWrapper placementWrapper;

    @BeforeEach
    public void init(){
        this.commonParser = new CommonParser();
        this.prizeDistributionParser = new PrizeDistributionParser(commonParser);
        this.prizeDistributionWrapper = prizeDistributionParser
                .parsePrizeDistribution("https://www.hltv.org/events/5219/iem-summer-2021");
        this.placementWrappers = prizeDistributionWrapper.getPlacementWrappers();
        this.placementWrapper = placementWrappers.get(0);
    }

    @Test
    @Order(1)
    public void parsePrizeDistributionShouldNotBeNull(){
        Assertions.assertNotEquals(null, prizeDistributionWrapper);
    }

    @Test
    @Order(2)
    public void parsePrizeDistributionPlacementWrappersShouldNotBeNull(){
        Assertions.assertNotEquals(null, placementWrappers);
    }

    @Test
    @Order(3)
    public void parsePrizeDistributionPlacementWrappersShouldContain16Placements(){
        Assertions.assertEquals(16, placementWrappers.size());
    }

    @Test
    @Order(4)
    public void parsePrizeDistributionPlacementWrapperShouldNotBeNull(){
        Assertions.assertNotEquals(null, placementWrapper);
    }

    @Test
    @Order(5)
    public void parsePrizeDistributionPlacementWrapperPrizeShouldNotBeNull(){
        Assertions.assertNotEquals(null, placementWrapper.getPrize());
    }

    @Test
    @Order(6)
    public void parsePrizeDistributionPlacementWrapperTeamShouldNotBeNull(){
        Assertions.assertNotEquals(null, placementWrapper.getTeamWrapper());
    }

    @Test
    @Order(7)
    public void parsePrizeDistributionPlacementWrapperPlaceShouldNotBeNull(){
        Assertions.assertNotEquals(null, placementWrapper.getPlace());
    }

    @Test
    @Order(8)
    public void parsePrizeDistributionPlacementWrapperTeamName(){
        Assertions.assertEquals("Gambit", placementWrapper.getTeamWrapper().getName());
    }

    @Test
    @Order(9)
    public void parsePrizeDistributionPlacementWrapperTeamImageUrl(){
        Assertions.assertEquals("https://www.hltv.org//img/static/flags/30x20/RU.gif",
                placementWrapper.getTeamWrapper().getImgUrl());
    }

    @Test
    @Order(10)
    public void parsePrizeDistributionPlacementWrapperPrize(){
        Assertions.assertEquals("$100,000",
                placementWrapper.getPrize());
    }

    @Test
    @Order(11)
    public void parsePrizeDistributionPlacementWrapperPlace(){
        Assertions.assertEquals("1st",
                placementWrapper.getPlace());
    }
}
