package com.dendeberia;

import com.dendeberia.domain.TeamWrapper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class EventTeamsParser {
    private CommonParser commonParser;

    public EventTeamsParser(CommonParser commonParser) {
        this.commonParser = commonParser;
    }

    public List<TeamWrapper> parseTeams(String url){
        Document page = commonParser.getPage(url);
        return parseTeams(page);
    }

    public List<TeamWrapper> parseTeams(Document page){
        List<TeamWrapper> teams = new ArrayList<>();
        Element teamsAttending = commonParser.getElementByClass(page, "teams-attending");
        Elements teamBoxes = commonParser.getElementsByClass(teamsAttending, "team-box");
        if(teamBoxes != null){
            for (Element teamBox : teamBoxes){
                teams.add(parseTeam(teamBox));
            }
        }
        return teams;
    }

    private TeamWrapper parseTeam(Element teamBox){
        TeamWrapper teamWrapper = new TeamWrapper();
        Element teamName = commonParser.getElementByClass(teamBox, "team-name");
        Element text = commonParser.getElementByClass(teamName, "text");
        if(text != null){
            teamWrapper.setName(text.text());
        }
        Element logoBox = commonParser.getElementByClass(teamBox, "logo-box");
        Element img = commonParser.getElementByTag(logoBox, "img");
        if (img != null){
            teamWrapper.setImgUrl(img.attr("src"));
        }
        return teamWrapper;
    }
}
