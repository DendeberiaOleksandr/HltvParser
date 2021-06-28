package com.dendeberia.domain;

import com.dendeberia.CommonParser;
import com.dendeberia.domain.event.placement.PlacementWrapper;
import com.dendeberia.domain.event.placement.PrizeDistributionWrapper;
import com.sun.istack.internal.Nullable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class PrizeDistributionParser {
    private CommonParser commonParser;

    public PrizeDistributionParser(CommonParser commonParser) {
        this.commonParser = commonParser;
    }

    public PrizeDistributionWrapper parsePrizeDistribution(String pageUrl){
        Document page = commonParser.getPage(pageUrl);
        return parsePrizeDistribution(page);
    }

    public PrizeDistributionWrapper parsePrizeDistribution(Document page){
            PrizeDistributionWrapper prizeDistributionWrapper = new PrizeDistributionWrapper();
            Elements placements = commonParser.getElementsByClass(page, "placement");
            if(placements != null){
                List<PlacementWrapper> placementWrappers = new ArrayList<>();
                for(Element placement: placements){
                    PlacementWrapper placementWrapper = parsePlacementWrapper(placement);
                    placementWrappers.add(placementWrapper);
                }
                prizeDistributionWrapper.setPlacementWrappers(placementWrappers);
            }

        return prizeDistributionWrapper;
    }

    private PlacementWrapper parsePlacementWrapper(Element placement){
        PlacementWrapper placementWrapper = new PlacementWrapper();
        Element team = commonParser.getElementByClass(placement, "team");
        TeamWrapper teamWrapper = parseTeam(team);
        placementWrapper.setTeamWrapper(teamWrapper);
        String place = parsePlace(placement);
        placementWrapper.setPlace(place);
        String prize = parsePrize(placement);
        placementWrapper.setPrize(prize);
        return placementWrapper;
    }

    @Nullable
    private String parsePrize(Element placement){
            Element prizeMoney = commonParser.getElementByClass(placement, "prizeMoney");
            if(prizeMoney != null){
                return prizeMoney.text();
            }

        return null;
    }

    @Nullable
    private String parsePlace(Element placement){
        Elements divs = placement.getElementsByTag("div");
        if(divs.size() > 3){
            Element div2 = divs.get(2);
            if(div2 != null){
                return div2.text();
            }
        }
        return null;
    }

    @Nullable
    private TeamWrapper parseTeam(Element team){
        if(team != null){
                TeamWrapper teamWrapper = new TeamWrapper();
                Element img = commonParser.getElementByTag(team, "img");
                if(img != null){
                    teamWrapper.setImgUrl("https://www.hltv.org/" + img.attr("src"));
                }
                Element link = commonParser.getElementByTag(team, "a");
                if(link != null){
                    teamWrapper.setName(link.text());
                }
                return teamWrapper;

        }
        return null;
    }
}
