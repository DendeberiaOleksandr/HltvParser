package com.dendeberia;

import com.dendeberia.domain.event.Event;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EventParserTest {

    private CommonParser commonParser;
    private DateParser dateParser;
    private EventParser eventParser;
    private Event event;

    @BeforeEach
    public void init(){
        commonParser = new CommonParser();
        dateParser = new DateParser(commonParser);
        eventParser = new EventParser(dateParser, commonParser);
        event = eventParser.getEvent("https://www.hltv.org/events/5604/blast-premier-spring-final-2021");
    }

    @Test
    @Order(1)
    public void parseEventName(){
        Assertions.assertEquals("BLAST Premier Spring Final 2021", event.getName());
    }

    @Test
    @Order(2)
    public void parseEventImageUrl(){
        Assertions.assertEquals("https://img-cdn.hltv.org/gallerypicture/" +
                "yvo8UDVXFzK7BPa-X4DShU.jpg?" +
                "ixlib=java-2.1.0" +
                "&s=f6b5e57e193407cd366bfe9fba0a3f38",
                event.getImgUrl());
    }

    @Test
    @Order(3)
    public void parseEventStartDate(){
        Assertions.assertNotEquals(null, event.getStartDate());
    }

    @Test
    @Order(4)
    public void parseEventEndDate(){
        Assertions.assertNotEquals(null, event.getEndDate());
    }

    @Test
    @Order(5)
    public void parseEventPrizePool(){
        Assertions.assertEquals("$425,000", event.getPrizePool());
    }

    @Test
    @Order(6)
    public void parseEventLocationName(){
        Assertions.assertEquals("Europe (Online)", event.getLocation().getName());
    }

    @Test
    @Order(7)
    public void parseEventLocationImageUrl(){
        Assertions.assertEquals("https://www.hltv.org/img/static/flags/30x20/EU.gif",
                event.getLocation().getImgUrl());
    }
}
