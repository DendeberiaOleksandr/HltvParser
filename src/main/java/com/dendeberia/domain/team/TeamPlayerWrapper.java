package com.dendeberia.domain.team;

import com.dendeberia.domain.PlayerWrapper;

public class TeamPlayerWrapper {
    private PlayerWrapper playerWrapper;
    private int maps;

    public PlayerWrapper getPlayerWrapper() {
        return playerWrapper;
    }

    public void setPlayerWrapper(PlayerWrapper playerWrapper) {
        this.playerWrapper = playerWrapper;
    }

    public int getMaps() {
        return maps;
    }

    public void setMaps(int maps) {
        this.maps = maps;
    }
}
