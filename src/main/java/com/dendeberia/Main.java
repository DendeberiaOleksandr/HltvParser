package com.dendeberia;


import com.dendeberia.domain.event.Event;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static final String HLTV_PREFIX = "https://www.hltv.org";
    public static void main(String[] args) throws IOException, ParseException {
        CommonParser commonParser = new CommonParser();
        EventParser parser = new EventParser(new DateParser(commonParser), commonParser);
        Event event = parser.getEvent("https://www.hltv.org/events/5604/blast-premier-spring-final-2021");
        System.out.println(event);
    }
}
