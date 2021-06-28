package com.dendeberia;

import com.dendeberia.domain.TeamWrapper;
import com.dendeberia.domain.event.BracketNode;
import com.dendeberia.domain.event.DoubleEliminationBracket;
import com.dendeberia.domain.event.SingleEliminationBracket;
import com.dendeberia.domain.event.BracketRound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BracketParser {
    private static final Logger log = LogManager.getLogger();
    private DateParser dateParser;
    private CommonParser commonParser;

    public BracketParser(CommonParser commonParser,
                         DateParser dateParser) {
        this.commonParser = commonParser;
        this.dateParser = dateParser;
    }

    public DoubleEliminationBracket parseBracket(String pageUrl) throws IOException {
        DoubleEliminationBracket doubleEliminationBracket = new DoubleEliminationBracket();

        Document page = commonParser.getPage(pageUrl);

        Element bracket = commonParser.getElementByClass(page, "slotted-bracket");

        if(bracket != null){
            doubleEliminationBracket = parseDoubleEliminationBracket(bracket);
        }

        return doubleEliminationBracket;
    }

    private DoubleEliminationBracket parseDoubleEliminationBracket(Element bracket) throws IOException {
        DoubleEliminationBracket doubleEliminationBracket = new DoubleEliminationBracket();
        if(bracket != null){
            log.info("parseDoubleEliminationBracket: " + bracket.toString());

            Elements bracketElements = commonParser.getElementsByClass(bracket, "slotted-bracket-tier");
            if(bracketElements != null){

                //TODO
                if(bracketElements.size() == 2){
                    Element winnersBracketElement = bracketElements.get(0);
                    Element losersBracketElement = bracketElements.get(1);

                    SingleEliminationBracket winnersBracket = parseSingleEliminationBracket(winnersBracketElement);
                    SingleEliminationBracket losersBracket = parseSingleEliminationBracket(losersBracketElement);

                    doubleEliminationBracket.setWinnersBracket(winnersBracket);
                    doubleEliminationBracket.setLosersBracket(losersBracket);
                }
            }
        }

        return doubleEliminationBracket;
    }

    private BracketNode parseNodeFromSlotWrapper(Element slotWrapper) throws IOException {
        BracketNode node = new BracketNode();
        if(slotWrapper != null){
            Date date = dateParser.parseDateFromSlotWrapperHeader(slotWrapper);
            node.setDate(date);

            Element upTeamElement = commonParser.getElementByClass(slotWrapper, "team1");
            if(upTeamElement != null){
                TeamWrapper teamUp = parseTeamFromTeamSlot(upTeamElement);
                node.setUpTeam(teamUp);
                node.setUpScore(parseResultFromTeamSlot(upTeamElement));
            }
            Element downTeamElement = commonParser.getElementByClass(slotWrapper, "team2");
            if(downTeamElement != null){
                TeamWrapper teamDown = parseTeamFromTeamSlot(downTeamElement);
                node.setUpTeam(teamDown);
                node.setDownScore(parseResultFromTeamSlot(downTeamElement));
            }
            Element winner = commonParser.getElementByClass(slotWrapper, "winner");
            Element loser = commonParser.getElementByClass(slotWrapper, "loser");
            if(winner != null && loser != null){
                TeamWrapper winnerTeam = parseTeamFromTeamSlot(winner);
                TeamWrapper upTeam = node.getUpTeam();
                if(upTeam != null){
                    if(upTeam.getName().equals(winnerTeam.getName())){
                        node.setTeamWinner(node.getUpTeam());
                    } else {
                        node.setTeamWinner(node.getDownTeam());
                    }
                }
            }
        }
        return node;
    }

    private int parseResultFromTeamSlot(Element teamSlot) throws IOException {
        Element result = commonParser.getElementByClass(teamSlot, "result");
        if(result != null){
            String resultText = result.text();
            return Integer.parseInt(resultText);
        }

        return 0;
    }

    private TeamWrapper parseTeamFromTeamSlot(Element teamSlot) throws IOException {
        TeamWrapper bracketNodeTeam = new TeamWrapper();
        if(teamSlot != null){
            Element imgElement = commonParser.getElementByClass(teamSlot, "team-image");
            if(imgElement != null){
                bracketNodeTeam.setImgUrl(imgElement.attr("src"));
            }
            Element teamNameElement = commonParser.getElementByClass(teamSlot, "team-name");
            if(teamNameElement != null){
                bracketNodeTeam.setName(teamNameElement.text());
            }
        }

        return bracketNodeTeam;
    }

    private SingleEliminationBracket parseSingleEliminationBracket(Element bracket) throws IOException {
        SingleEliminationBracket singleEliminationBracket = new SingleEliminationBracket();

        Elements rounds = commonParser.getElementsByClass(bracket, "round");

        if(rounds != null){
            List<BracketRound> bracketRounds = new ArrayList<>();
            for(Element round : rounds){
                BracketRound bracketRound = new BracketRound();

                Element roundHeader = commonParser.getElementByClass(round, "round-header");
                String roundName = roundHeader.text();
                bracketRound.setName(roundName);

                List<BracketNode> bracketNodes = new ArrayList<>();
                Elements slots = commonParser.getElementsByClass(round, "slot-wrapper");
                for(Element slot : slots){
                    BracketNode bracketNode = parseNodeFromSlotWrapper(slot);
                    bracketNodes.add(bracketNode);
                }
                bracketRound.setNodes(bracketNodes);
            }
            singleEliminationBracket.setRounds(bracketRounds);
        }

        return singleEliminationBracket;

    }

    private String parseBracketName(Element bracket) throws IOException {
        Element bracketTitle = commonParser.getElementByClass(bracket, "slotted-bracket-header");
        Element span = commonParser.getElementByTag(bracketTitle, "span");
        return span.text();
    }
}
