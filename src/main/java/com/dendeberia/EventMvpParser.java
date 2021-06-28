package com.dendeberia;

import com.dendeberia.domain.PlayerWrapper;
import com.dendeberia.domain.event.mvp.EventMvpWrapper;
import com.dendeberia.domain.event.mvp.PlayerStatsWrapper;
import com.sun.istack.internal.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class EventMvpParser {
    private CommonParser commonParser;
    private static final Logger log = LogManager.getLogger();

    public EventMvpParser(CommonParser commonParser) {
        this.commonParser = commonParser;
    }

    public EventMvpWrapper parseEventMvpWrapper(String pageUrl) {
        Document page = commonParser.getPage(pageUrl);
        return parseEventMvpWrapper(page);
    }

    public EventMvpWrapper parseEventMvpWrapper(Document page) {
        EventMvpWrapper eventMvpWrapper = new EventMvpWrapper();
        Element boxMvp = commonParser.getElementByClass(page, "standard-box mvp");
        Element graph = commonParser.getElementByClass(boxMvp, "graph");
        PlayerStatsWrapper playerStatsWrapper = parseMvpPlayerStats(graph);
        PlayerWrapper playerWrapper = parseMvpPlayer(boxMvp);
        eventMvpWrapper.setPlayer(playerWrapper);
        eventMvpWrapper.setPlayerStatsWrapper(playerStatsWrapper);
        Element mvpCoin = commonParser.getElementByClass(boxMvp, "mvp-coin");
        if(mvpCoin != null){
            eventMvpWrapper.setCoinImgUrl("https://www.hltv.org" + mvpCoin.attr("src"));
        }

        return eventMvpWrapper;
    }

    @Nullable
    private PlayerWrapper parseMvpPlayer(Element standardMvpBox){
        PlayerWrapper playerWrapper = new PlayerWrapper();
        Element mvpPicture = commonParser.getElementByClass(standardMvpBox, "mvp-picture");
        if(mvpPicture != null){
            Element img = commonParser.getElementByTag(mvpPicture, "img");
            if(img != null){
                playerWrapper.setImgUrl(img.attr("src"));
            }
        }
        Element playerName = commonParser.getElementByClass(standardMvpBox, "player-name");
        Element flag = commonParser.getElementByClass(playerName, "flag");
        if(flag != null){
            playerWrapper.setCountryImgUrl("https://www.hltv.org" + flag.attr("src"));
        }
        Element playerLink = commonParser.getElementByTag(playerName, "a");
        if(playerLink != null){
            String text = playerLink.text();
            String name = text.replaceAll("'", "");
            playerWrapper.setName(name);
        }

        return playerWrapper;
    }

    @Nullable
    private PlayerStatsWrapper parseMvpPlayerStats(Element graph){
        if(graph != null){
            String config = graph.attr("data-fusionchart-config");
            if(config != null){
                PlayerStatsWrapper playerStatsWrapper = new PlayerStatsWrapper();
                String data = config.substring(config.indexOf('['), config.indexOf("]}"));
                String[] dataSplit = data.split(",[{}]");
                for(String dataSplitRow : dataSplit){
                    String replacedDataWithEnd = dataSplitRow.replaceAll("[\\[{}\"]", "");
                    String replacedData = replacedDataWithEnd.replaceAll("].+", "");
                    String[] dataPairs = replacedData.split(",");
                    String[] key = dataPairs[0].split(":");
                    String[] value = dataPairs[dataPairs.length - 1].split(":");
                    try {
                        if(key[1].equals("KPR")){
                            playerStatsWrapper.setKillsPerRound(Double.parseDouble(value[1]));
                        } else if(key[1].equals("DPR")){
                            playerStatsWrapper.setDeathsPerRound(Double.parseDouble(value[1]));
                        } else if(key[1].equals("KAST")){
                            playerStatsWrapper.setKillAssistSurvivedTraded(value[1]);
                        } else if(key[1].equals("Impact")){
                            playerStatsWrapper.setImpact(Double.parseDouble(value[1]));
                        } else if(key[1].equals("ADR")){
                            playerStatsWrapper.setDamagePerRound(Double.parseDouble(value[1]));
                        } else if(key[1].equals("Rating 2.0")){
                            playerStatsWrapper.setRating2(Double.parseDouble(value[1]));
                        }
                            } catch (NumberFormatException e){
                                log.error("parseMvpPlayerStats: problem with parsing double.");
                            }

                }


                return playerStatsWrapper;
            }
        }
        return null;
    }

}
