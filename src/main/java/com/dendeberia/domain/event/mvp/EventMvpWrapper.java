package com.dendeberia.domain.event.mvp;

import com.dendeberia.domain.PlayerWrapper;

public class EventMvpWrapper {
    private PlayerWrapper player;
    private String coinImgUrl;
    private PlayerStatsWrapper playerStatsWrapper;

    public PlayerWrapper getPlayer() {
        return player;
    }

    public void setPlayer(PlayerWrapper player) {
        this.player = player;
    }

    public String getCoinImgUrl() {
        return coinImgUrl;
    }

    public void setCoinImgUrl(String coinImgUrl) {
        this.coinImgUrl = coinImgUrl;
    }

    public PlayerStatsWrapper getPlayerStatsWrapper() {
        return playerStatsWrapper;
    }

    public void setPlayerStatsWrapper(PlayerStatsWrapper playerStatsWrapper) {
        this.playerStatsWrapper = playerStatsWrapper;
    }
}
