package com.dendeberia;

import com.dendeberia.domain.PrizeDistributionParser;
import com.dendeberia.domain.event.Event;
import com.dendeberia.domain.Location;
import com.sun.istack.internal.Nullable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class EventParser {

    private DateParser dateParser;
    private CommonParser commonParser;
    private EventMvpParser eventMvpParser;
    private ResultParser resultParser;
    private PrizeDistributionParser prizeDistributionParser;

    public EventParser(DateParser dateParser,
                       CommonParser commonParser) {
        this.dateParser = dateParser;
        this.commonParser = commonParser;
        this.eventMvpParser = new EventMvpParser(commonParser);
        this.resultParser = new ResultParser(commonParser, dateParser);
        this.prizeDistributionParser = new PrizeDistributionParser(commonParser);
    }

    private final String eventsArchiveUrlString = "https://www.hltv.org/events/archive";
    private final String imagePrefix = "https://www.hltv.org";

    private Document getArchiveEventsPage(int offset) throws IOException {
        Document page = Jsoup.connect(eventsArchiveUrlString)
                .data("offset", String.valueOf(offset))
                .get();

        if(page != null){
            return page;
        }

        throw new IOException("Problem with connection to events archive url. Offset: " + offset);
    }

    @Nullable
    public Event getEvent(String pageUrl){
        Document page = commonParser.getPage(pageUrl);
        try {
            Event event = getEvent(page);
            String prefixEventId = pageUrl.replaceAll(".+events\\/", "");
            String eventId = prefixEventId.replaceAll("\\/.+", "");
            event.setResults(resultParser.parseResults("https://www.hltv.org/results?event=" + eventId));
            return event;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Event getEvent(Document eventPage) throws IOException, ParseException {
        Event event = new Event();

        Element contentColElement = commonParser.getElementByClass(eventPage, "contentCol");

        //Event header
        Elements eventHeaders = commonParser.getElementsByClass(eventPage, "event-header-component");
        if(eventHeaders != null){
            if(eventHeaders.size() == 2){
                Element mainEventHeader = eventHeaders.get(0);

                //Img url parsing
                Element img = commonParser.getElementByClass(mainEventHeader, "event-img");
                if(img != null){
                    String imgUrl = img.attr("src");
                    event.setImgUrl(imgUrl);
                }

                //Name parsing
                Element eventNameHeader = commonParser.getElementByClass(mainEventHeader, "eventname");
                if(eventNameHeader != null){
                    String eventName = eventNameHeader.text();
                    event.setName(eventName);
                }

                Element smallEventHeader = eventHeaders.get(1);
                Element smallHeaderTableBody = commonParser.getElementByTag(smallEventHeader, "tbody");

                //Date parsing
                Element eventdate = commonParser.getElementByClass(smallHeaderTableBody, "eventdate");
                List<String> eventDates = getEventDatesFromTableCell(eventdate);
                List<Date> dates = dateParser.parseDates(eventDates);
                event.setStartDate(dates.get(0));
                event.setEndDate(dates.get(1));

                //Main prizepool parsing
                Element prizepool = commonParser.getElementByClass(smallHeaderTableBody, "prizepool");
                if(prizepool != null){
                    event.setPrizePool(prizepool.text());
                }

                //Location
                Element locationElement = commonParser.getElementByClass(smallHeaderTableBody, "location");
                Element locationImg = commonParser.getElementByTag(locationElement, "img");
                Element locationSpan = commonParser.getElementByTag(locationElement, "span");
                Location location = new Location();
                if(locationImg != null){
                    location.setImgUrl(imagePrefix + locationImg.attr("src"));
                }
                if(locationSpan != null){
                    location.setName(locationSpan.text());
                }
                event.setLocation(location);
            }
        }

        //Event mvp
        event.setEventMvp(eventMvpParser.parseEventMvpWrapper(eventPage));

        //Prize distribution
        event.setPrizeDistributionWrapper(prizeDistributionParser.parsePrizeDistribution(eventPage));

        return event;
    }

    

    private List<String> getEventDatesFromTableCell(Element dateTd) throws IOException {
        List<String> dates = new ArrayList<String>();
        Elements spans = commonParser.getElementsByTag(dateTd, "span");

        for(int i = 0; i < spans.size(); i++){
            Element span = spans.get(i);
            String spanText = span.text();

            if(spanText.matches("[^-]+")){
                dates.add(spanText);
            }
        }

        return dates;
    }

    public List<String> getArchiveEventLinks(int offset){
        List<String> links = new ArrayList<>();
        Document page = commonParser.getPage("https://www.hltv.org/events/archive?offset=" + offset);
        Element contentColElement = commonParser.getElementByClass(page, "contentCol");
        if(contentColElement != null){
            Elements eventLinks = contentColElement.getElementsByClass("small-event");

            if(eventLinks != null){
                for(int i = 0; i < eventLinks.size(); i++){
                    Element eventLink = eventLinks.get(i);

                    if(eventLink != null){
                        String href = eventLink.attr("href");
                        links.add("https://www.hltv.org/" + href);
                    }
                }
            }
        }
        return links;
    }


    public int getArchiveEventsCount() throws IOException {
        Document eventsArchivePage = commonParser.getPage("");

        Elements topPaginationElements = eventsArchivePage.getElementsByClass("pagination-top");

        if(topPaginationElements != null){
            Element topPagination = topPaginationElements.get(0);

            if(topPagination != null){
                Elements paginationDataElements = topPagination.getElementsByClass("pagination-data");

                if(paginationDataElements != null){
                    Element paginationDataElement = paginationDataElements.get(0);

                    if(paginationDataElement != null){
                        String paginationData = paginationDataElement.text();

                        String[] split = paginationData.split(" ");

                        String eventCountString = split[split.length - 1];

                        return Integer.parseInt(eventCountString);
                    }
                }
            }
        }

        throw new IOException("Problem with parsing events count");
    }
}
