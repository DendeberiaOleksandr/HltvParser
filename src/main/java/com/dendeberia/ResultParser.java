package com.dendeberia;

import com.dendeberia.domain.TeamWrapper;
import com.dendeberia.domain.event.result.ResultDayWrapper;
import com.dendeberia.domain.event.result.ResultRowWrapper;
import com.sun.istack.internal.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResultParser {
    private static final Logger log = LogManager.getLogger();
    private CommonParser commonParser;
    private DateParser dateParser;

    public ResultParser(CommonParser commonParser,
                        DateParser dateParser) {
        this.commonParser = commonParser;
        this.dateParser = dateParser;
    }

    @Nullable
    public List<ResultDayWrapper> parseResults(String url) {
        Document page = commonParser.getPage(url);
        if(page != null){
                List<ResultDayWrapper> resultDayWrappers = new ArrayList<>();
                Element results = commonParser.getElementByClass(page, "results-all");
                if(results != null){
                    Elements resultsSublists = commonParser.getElementsByClass(results, "results-sublist");
                    if(resultsSublists != null){
                        for(Element resultsSublist : resultsSublists){
                            ResultDayWrapper resultDayWrapper = parseResultDayWrapper(resultsSublist);
                            if(resultDayWrapper != null){
                                resultDayWrappers.add(resultDayWrapper);
                            }
                        }
                    }
                }
                return resultDayWrappers;
        }
        return null;
    }

    @Nullable
    private ResultDayWrapper parseResultDayWrapper(Element resultsSublist){
        if (resultsSublist != null){
            try {
                ResultDayWrapper resultDayWrapper = new ResultDayWrapper();
                Element headLineElement = commonParser.getElementByClass(resultsSublist, "standard-headline");
                if(headLineElement != null){
                    Date date = dateParser.parseDate(headLineElement.text());
                    resultDayWrapper.setDate(date);
                }
                Elements trs = commonParser.getElementsByTag(resultsSublist, "tr");
                if(trs != null){
                    List<ResultRowWrapper> resultRowWrappers = new ArrayList<>();
                    for(Element tr: trs){
                        resultRowWrappers.add(parseResultRow(tr));
                    }
                    resultDayWrapper.setResults(resultRowWrappers);
                }
                return resultDayWrapper;
            } catch (ParseException e) {
                e.printStackTrace();
                log.error("parseResultDayWrapper: Error with parsing.");
            }
        }

        return null;
    }

    @Nullable
    private ResultRowWrapper parseResultRow(Element resultTableRow){
        if(resultTableRow != null){
                ResultRowWrapper resultRowWrapper = new ResultRowWrapper();
                Elements teamCells = commonParser.getElementsByClass(resultTableRow, "team-cell");
                if(teamCells != null){
                    if(teamCells.size() == 2){
                        Element teamCell1 = teamCells.get(0);
                        Element teamCell2 = teamCells.get(1);
                        TeamWrapper teamWrapper1 = parseTeam(teamCell1);
                        TeamWrapper teamWrapper2 = parseTeam(teamCell2);
                        resultRowWrapper.setTeamWrapper1(teamWrapper1);
                        resultRowWrapper.setTeamWrapper2(teamWrapper2);
                    }
                }
                Element resultScore = commonParser.getElementByClass(resultTableRow, "result-score");
                short[] score = parseScore(resultScore);
                if(score != null){
                    resultRowWrapper.setTeam1Score(score[0]);
                    resultRowWrapper.setTeam2Score(score[1]);
                }
                Element starCell = commonParser.getElementByClass(resultTableRow, "star-cell");
                short stars = parseStars(starCell);
                if(stars != 0){
                    resultRowWrapper.setRating(stars);
                }
                String map = parseMap(starCell);
                if (map != null){
                    resultRowWrapper.setMap(map);
                }

                return resultRowWrapper;
        }
        return null;
    }

    @Nullable
    private String parseMap(Element starCell){
        if(starCell != null){
                Element mapElement = commonParser.getElementByClass(starCell, "map");
                if(mapElement != null){
                    return mapElement.text();
                }
        }
        return null;
    }

    @Nullable
    private short parseStars(Element starCell){
        if(starCell != null) {
                Elements stars = commonParser.getElementsByClass(starCell, "star");
                if (stars != null) {
                   return (short) stars.size();
                }
        }

        return 0;
    }

    @Nullable
    private short[] parseScore(Element resultScore){
        if(resultScore != null){
                Elements teamsScores = commonParser.getElementsByTag(resultScore, "span");
                if(teamsScores != null){
                    if(teamsScores.size() == 2){
                        short score[] = new short[2];
                        Element team1Score = teamsScores.get(0);
                        Element team2Score = teamsScores.get(1);

                        if(team1Score != null){
                            score[0] = Short.parseShort(team1Score.text());
                        }

                        if(team2Score != null){
                            score[1] = Short.parseShort(team2Score.text());
                        }
                        return score;
                    }
                }
        }

        return null;
    }

    @Nullable
    private TeamWrapper parseTeam(Element teamCell){
        if(teamCell != null){
                TeamWrapper teamWrapper = new TeamWrapper();
                Element teamElement = commonParser.getElementByClass(teamCell, "team");
                if(teamElement != null){
                    teamWrapper.setName(teamElement.text());
                }
                Element imgElement = commonParser.getElementByTag(teamCell, "img");
                if(imgElement != null){
                    teamWrapper.setImgUrl(imgElement.attr("src"));
                }
                return teamWrapper;
        }

        return null;
    }
}
