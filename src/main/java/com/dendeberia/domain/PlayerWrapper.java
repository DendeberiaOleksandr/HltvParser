package com.dendeberia.domain;

public class PlayerWrapper {
    private String name;
    private String statsProfileUrl;
    private String countryImgUrl;
    private String imgUrl;

    public String getStatsProfileUrl() {
        return statsProfileUrl;
    }

    public void setStatsProfileUrl(String statsProfileUrl) {
        this.statsProfileUrl = statsProfileUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryImgUrl() {
        return countryImgUrl;
    }

    public void setCountryImgUrl(String countryImgUrl) {
        this.countryImgUrl = countryImgUrl;
    }
}
