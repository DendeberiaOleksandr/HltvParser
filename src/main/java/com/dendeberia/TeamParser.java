package com.dendeberia;

import com.dendeberia.domain.PlayerWrapper;
import com.dendeberia.domain.Team;
import com.dendeberia.domain.team.TeamPlayerWrapper;
import com.sun.istack.internal.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class TeamParser {

    private static final Logger log = LogManager.getLogger();
    private CommonParser commonParser;

    public TeamParser(CommonParser commonParser) {
        this.commonParser = commonParser;
    }

    public int[] parseYears(String statsPageUrl){
        Document page = commonParser.getPage(statsPageUrl);
        return parseYears(page);
    }

    @Nullable
    public int[] parseYears(Document statsPage){
        Element filters = commonParser.getElementByClass(statsPage, "filter-column-con");
        Elements filterHeaders = commonParser.getElementsByClass(filters, "filter-column-header");
        Elements filterContents = commonParser.getElementsByClass(filters, "filter-column-content");

        if(filterHeaders != null && filterContents != null){
            if(filterHeaders.size() == filterContents.size()){
                for(int i = 0; i < filterHeaders.size(); i++){
                    Element filterHeader = filterHeaders.get(i);
                    if(filterHeader != null){
                        String headerText = filterHeader.text();
                        if(headerText != null){
                            if(headerText.equals("Time")){
                                Element timeContent = filterContents.get(i);
                                return parseYears(timeContent);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    @Nullable
    private int[] parseYears(Element timeContent){
        Elements timeLinks = commonParser.getElementsByTag(timeContent, "a");
        if(timeLinks != null){
            int[] years = new int[timeLinks.size()];
            for (int i = 0; i < timeLinks.size(); i++){
                Element timeLink = timeLinks.get(i);
                if(timeLink != null){
                    String timeLinkText = timeLink.text();
                    if(timeLinkText != null){
                        if(timeLinkText.matches("^[0-9]+$")){
                            try{
                                years[i] = Integer.parseInt(timeLinkText);
                            } catch (NumberFormatException e){
                                log.error("parseYears: Error when parsing int from string: " + timeLinkText);
                            }
                        }
                    }
                }
            }
            return years;
        }
        return null;
    }

    public List<String> parseTeamUrlListFromStatsPage(int year){
        return parseTeamUrlListFromStatsPage("https://www.hltv.org/stats/" +
                "teams?startDate=" + year + "-01-01&" +
                "endDate=" + year + "-12-31");
    }

    public List<String> parseTeamUrlListFromStatsPage(String pageUrl){
        Document page = commonParser.getPage(pageUrl);
        return parseTeamUrlListFromStatsPage(page);
    }

    @Nullable
    public List<String> parseTeamUrlListFromStatsPage(Document page){
        Element statsTable = commonParser.getElementByClass(page, "stats-table");
        Element statsTableBody = commonParser.getElementByTag(statsTable, "tbody");
        Elements tableRows = commonParser.getElementsByTag(statsTableBody, "tr");
        if(tableRows != null){
            List<String> teamUrls = new ArrayList<>();
            for(int i = 0; i < tableRows.size(); i++){
                Element tableRow = tableRows.get(i);
                Element teamOverview = commonParser.getElementByClass(tableRow,
                        "teamCol-teams-overview");
                Element teamLink = commonParser.getElementByTag(teamOverview, "a");
                if(teamLink != null){
                    String teamLinkText = Main.HLTV_PREFIX + teamLink.attr("href");
                    String teamStatsLink = teamLinkText.replace("\\?[a-zA-Z]+", "");
                    teamUrls.add(teamStatsLink);
                }
            }
            return teamUrls;
        }
        return null;
    }

    public Team parseTeam(String pageUrl){
        Document page = commonParser.getPage(pageUrl);
        return parseTeamStats(page);
    }

    @Nullable
    private Long parseTeamId(Document teamStatsPage){
        Element statsSection = commonParser.getElementByClass(teamStatsPage, "stats-section");
        Element teamProfile = commonParser.getElementByClass(statsSection, "button");
        if(teamProfile != null){
            return parseTeamId(teamProfile.attr("href"));
        }
        return null;
    }

    @Nullable
    private Long parseTeamId(String pageUrl){
        if(pageUrl != null){
            String id = pageUrl.replaceAll("[^0-9]", "");
            try {
                return Long.parseLong(id);
            } catch (NumberFormatException e){
                log.error("parseTeamId: Error when parsing long from string: " + pageUrl);
            }
        }
        return null;
    }

    private List<TeamPlayerWrapper> parseCurrentPlayers(Document statsSection){
        Element grid = commonParser.getElementByClass(statsSection, "grid", 0);
        return parsePlayersFromGrid(grid);
    }

    private List<TeamPlayerWrapper> parseHistoricalPlayers(Document statsSection){
        Element grid = commonParser.getElementByClass(statsSection, "grid", 1);
        return parsePlayersFromGrid(grid);
    }

    private List<TeamPlayerWrapper> parseStandinPlayers(Document statsSection){
        Element grid = commonParser.getElementByClass(statsSection, "grid", 2);
        return parsePlayersFromGrid(grid);
    }

    @Nullable
    private List<TeamPlayerWrapper> parsePlayersFromGrid(Element grid){
        Elements cols = commonParser.getElementsByClass(grid, "col");
        if(cols != null){
            List<TeamPlayerWrapper> players = new ArrayList<>();
            for(int i = 0; i < cols.size(); i++){
                players.add(parsePlayerFromGrid(cols.get(i)));
            }
            return players;
        }
        return null;
    }

    @Nullable
    private TeamPlayerWrapper parsePlayerFromGrid(Element col){
        if(col != null){
            TeamPlayerWrapper teamPlayerWrapper = new TeamPlayerWrapper();
            PlayerWrapper player = new PlayerWrapper();
            Element img = commonParser.getElementByTag(col, "img");
            if(img != null){
                player.setName(img.attr("alt"));
                player.setImgUrl(img.attr("src"));
            }
            Element teammateInfo = commonParser.getElementByClass(col, "teammate-info");
            Element playerLink = commonParser.getElementByTag(teammateInfo, "a");
            if(playerLink != null){
                player.setStatsProfileUrl(Main.HLTV_PREFIX + playerLink.attr("href"));
                Element countryImg = commonParser.getElementByTag(playerLink, "img");
                if(countryImg != null){
                    player.setCountryImgUrl(Main.HLTV_PREFIX + countryImg.attr("src"));
                }
            }
            Element mapsSpan = commonParser.getElementByTag(teammateInfo, "span");
            if(mapsSpan != null){
                String mapsText = mapsSpan.text();
                String mapsCount = mapsText.replaceAll("[^0-9]", "");
                try {
                    teamPlayerWrapper.setMaps(Integer.parseInt(mapsCount));
                } catch (NumberFormatException e){
                    log.error("parsePlayerFromGrid: Error when parsing int from string: " + mapsCount);
                }
            }
            teamPlayerWrapper.setPlayerWrapper(player);
            return teamPlayerWrapper;
        }
        return null;
    }

    public Team parseTeamStats(Document page){

    }
}
